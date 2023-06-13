package com.minwei.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.minwei.common.Result;
import com.minwei.constant.AliConstants;
import com.minwei.pojo.User;
import com.minwei.mapper.UserMapper;
import com.minwei.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minwei.utils.SendMsg;
import com.minwei.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 发送短信验证码
     * @param user
     * @param session
     * @return
     */
    @Override
    public Result<String> sendMsg(User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            //生成随机4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //调用阿里云短信服务完成发送短信
            SendMsg.sendMessage(AliConstants.SIGN_NAME,AliConstants.TEMPLATE_CODE,phone,code);
            //将生成的验证码保存至Session
            session.setAttribute(phone,code);
            return Result.success("手机验证码发送成功");
        }
        return Result.success("手机验证码发送失败");
    }

    /**
     * 登录接口
     * @param map
     * @param session
     * @return
     */
    @Override
    public Result<User> login(Map map, HttpSession session) {
        log.info(map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //获取session中的验证码
        Object codeInSession = session.getAttribute(phone);
        //进行验证码比对
        if (codeInSession!=null && codeInSession.equals(code)){
            //登录成功
            //判断是否为新用户，如是则自动注册
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getPhone,phone);
            User user = userMapper.selectOne(wrapper);
            if (user==null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userMapper.insert(user);
            }
            //登录成功，将用户ID存入Session
            session.setAttribute("user",user.getId());
            return Result.success(user);
        }
        //比对登录失败
        return Result.error("登录失败");
    }
}
