package com.minwei.serviceImpl;

import com.pojo.Employee;
import com.mapper.EmployeeMapper;
import com.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
