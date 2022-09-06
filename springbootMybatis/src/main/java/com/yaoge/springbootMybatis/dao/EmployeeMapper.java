package com.yaoge.springbootMybatis.dao;


import com.yaoge.springbootMybatis.pojo.Employee;
import com.yaoge.springbootMybatis.pojo.People;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {

    int insert(@Param("employee") Employee employee);

    Employee getById(@Param("id") Integer id);
}
