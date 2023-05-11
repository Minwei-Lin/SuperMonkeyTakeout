package com.minwei.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minwei.common.Result;
import com.minwei.mapper.EmployeeMapper;
import com.minwei.pojo.Employee;
import com.minwei.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired EmployeeMapper employeeMapper;

    @Override
    public Result login(HttpServletRequest request, Employee employee) {
        //1.将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp =employeeMapper.selectOne(queryWrapper);
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
}
