package com.minwei.controller;


import com.minwei.pojo.Employee;
import com.minwei.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author minwei
 * @since 2023-05-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


}

