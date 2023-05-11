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
import java.util.Date;

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

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
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
            return Result.error("账户不存在");
        }
        //比对密码是否一致
        if (!emp.getPassword().equals(password)){
            return Result.error("密码错误");
        }
        //查看员工状态是否被禁用
        if (emp.getStatus()==0){
            //账号被禁用，提示信息
            return Result.error("该账号已被禁用");
        }
        //账号未被禁用，则将员工ID存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return Result.success("登录成功");
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result loginOut(HttpServletRequest request){
        try {
            //清理Session中保存的当前员工的ID
            request.getSession().removeAttribute("employee");
        }catch (Exception e){
            //删除失败
            return Result.error("登出失败");
        }
        return  Result.success("登出成功");
    }
    @PostMapping("/addEmployee")
    public Result addEmployee(HttpServletRequest request, @RequestBody Employee employee){
        //设置初始密码123456，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //添加员工时间
        employee.setCreateTime(new Date());
        //更改员工的时间
        employee.setUpdateTime(new Date());
        //获取当前登录的管理员的ID
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeService.save(employee);
        return Result.success("新增员工成功");
    }
}

