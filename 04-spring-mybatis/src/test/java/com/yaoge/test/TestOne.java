package com.yaoge.test;


import com.yaoge.dao.PeopleMapper;
import com.yaoge.pojo.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * create by yaoge
 * 2022/8/31 18:00
 */
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(value = false)
public class TestOne {


    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    PeopleMapper peopleMapper;

    @Test
    public void testSe(){

        List<People> list=peopleMapper.selectPeoples();
        list.forEach(System.out::println);
    }

    @Transactional//还是要配置完 Spring 事务管理器才能够生效
    @Test
    public void testIn(){



        peopleMapper.insertPeople();
        int i=10/0;

    }
}
