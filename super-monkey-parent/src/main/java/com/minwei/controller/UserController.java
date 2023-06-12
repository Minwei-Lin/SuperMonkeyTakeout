package com.minwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.minwei.common.Result;
import com.minwei.constant.AliConstants;
import com.minwei.mapper.UserMapper;
import com.minwei.pojo.User;
import com.minwei.service.UserService;
import com.minwei.utils.SendMsg;
import com.minwei.utils.ValidateCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 发送手机验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user, HttpSession session) throws Exception {
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
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody Map map, HttpSession session){
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //获取session中的验证码
        String codeInSession = session.getAttribute("phone").toString();
        //进行验证码比对
        if (codeInSession!=null && codeInSession.equals(code)){
            //登录成功
            //判断是否为新用户，如是则自动注册
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getPhone,phone);
            User user = userService.getOne(wrapper);
            if (user==null){
                User newUser = new User();
                newUser.setPhone(phone);
                newUser.setStatus(1);
                userService.save(newUser);
            }
            //登录成功，删除验证码
            session.setAttribute("user",user.getId());
            return Result.success(user);
        }
        //比对登录失败
        return Result.error("登录失败");
    }
}

