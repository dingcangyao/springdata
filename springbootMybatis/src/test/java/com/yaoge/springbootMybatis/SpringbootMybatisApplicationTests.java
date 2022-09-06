package com.yaoge.springbootMybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaoge.springbootMybatis.dao.EmployeeMapper;
import com.yaoge.springbootMybatis.dao.PeopleMapper;
import com.yaoge.springbootMybatis.pojo.Employee;
import com.yaoge.springbootMybatis.pojo.People;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.yaoge.springbootMybatis.dao")
class SpringbootMybatisApplicationTests {

    @Autowired
    PeopleMapper peopleMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        List<People> people = peopleMapper.selectPeoples();
        people.forEach(System.out::println);
    }
    @Test
    void testPages() {
        PageHelper.startPage(1,5);
        List<People> list = peopleMapper.selectPeoples();
        PageInfo<People> peoplePageInfo = new PageInfo<>(list);
        list.forEach(System.out::println);
    }

    @Test
    void testTypehandler(){
        Employee byId = employeeMapper.getById(1);
        System.out.println(byId);
    }

}
