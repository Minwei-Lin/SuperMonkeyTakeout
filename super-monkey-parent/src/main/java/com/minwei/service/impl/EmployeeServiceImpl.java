package com.minwei.service.impl;

import com.minwei.pojo.Employee;
import com.minwei.mapper.EmployeeMapper;
import com.minwei.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author minwei
 * @since 2023-05-09
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
