package com.venven.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.venven.reggie.common.Result;
import com.venven.reggie.entity.Employee;
import com.venven.reggie.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        // 1、表单拿到的密码 -> md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2、表单拿到的用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 3、没有则返回查询登录失败
        if(emp == null){
            return Result.error("登录失败，用户名或密码错误");
        }

        // 4、密码比对，不一致则返回登录失败
        if(!emp.getPassword().equals(password)){
            return Result.error("登录失败，用户名或密码错误");
        }

        // 5、查看员工状态，已禁用，返回员工已禁用结果
        if (emp.getStatus() == 0){
            return Result.error("账号已禁用");
        }

        // 6、登录成功，将员工id存入session中返回登录成功
        request.getSession().setAttribute("employee", emp.getId());
        return Result.success(emp);
    }
}
