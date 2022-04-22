package com.venven.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venven.reggie.entity.Employee;
import com.venven.reggie.mapper.IEmployeeMapper;
import com.venven.reggie.service.IEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<IEmployeeMapper, Employee> implements IEmployeeService {
}
