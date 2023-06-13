package com.minwei.service;

import com.minwei.common.Result;
import com.minwei.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
public interface UserService extends IService<User> {

    Result<String> sendMsg(User user, HttpSession session);

    Result<User> login(Map map, HttpSession session);
}
