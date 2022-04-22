package com.venven.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.venven.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IEmployeeMapper extends BaseMapper<Employee> {
}
