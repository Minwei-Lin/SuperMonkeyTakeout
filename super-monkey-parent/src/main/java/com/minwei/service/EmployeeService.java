package com.minwei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minwei.common.Result;
import com.minwei.pojo.Employee;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author minwei
 * @since 2023-05-10 11:07:25
 */
public interface EmployeeService extends IService<Employee> {

    Result login(HttpServletRequest request, Employee employee);
}
