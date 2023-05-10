package com.minwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.minwei.common.Result;
import com.minwei.pojo.Employee;
import com.minwei.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody Employee employee){
        //1.将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp =employeeService.getOne(queryWrapper);
        //3.没有查询到，则返回登录失败的结果
        if (emp==null){
            return new Result(401,"用户名不存在");
        }
        //比对密码是否一致
        if (!emp.getPassword().equals(password)){
            return new Result(401,"密码错误");
        }
        //查看员工状态是否被禁用
        if (emp.getStatus()==0){
            //账号被禁用，提示信息
            return new Result(401,"该账号已被禁用");
        }
        //账号未被禁用，则将员工ID存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return new Result(200,"登录成功");
    }
}

