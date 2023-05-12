package com.minwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.minwei.common.Result;
import com.minwei.pojo.Employee;
import com.minwei.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody Employee employee){
        employeeService.login(request,employee);
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
        return Result.success(emp);
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

    /**
     * 添加用户
     * @param employee
     * @return
     */
    @PostMapping("/addEmployee")
    public Result addEmployee(@RequestBody Employee employee){
        //设置初始密码123456，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);
        return Result.success("新增员工成功");
    }

    /**
     * 分页查询员工信息
     * @param page 当前页
     * @param pageSize 每页记录时数
     * @param name 账户名
     * @return
     */
    @GetMapping("/page")
    public Result page(int page,int pageSize,String name){
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getUsername,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 根据ID修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Employee employee){
        employeeService.updateById(employee);
        return Result.success("员工信息修改成功");
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        Employee emp = employeeService.getById(id);
        return Result.success(emp);
    }
}

