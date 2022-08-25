package com.yaoge.test;

import com.yaoge.config.SpringDataJpaConfiguration;
import com.yaoge.entity.Customer;
import com.yaoge.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * create by yaoge
 * 2022/8/24 17:03
 *
 * 基于junit4的单元测试
 */
//@ContextConfiguration("/spring.xml")//配置文件配置方式
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)//java config 配置方式
@RunWith(SpringJUnit4ClassRunner.class)
public class Test1 {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 测试查询
     */
    @Test
    public void testR(){
        Iterable<Customer> all = customerRepository.findAll();
        all.forEach(item->{
            System.out.println(item);
        });
    }
    /**
     * 测试删除  会帮助我们查询，删除游离态的数据
     */
    @Test
    public void testD(){
        Customer customer = new Customer();
        customer.setCustId(1L);
        customerRepository.delete(customer);
    }
    /**
     * 测试添加
     * 指定Id也没有用，依然是默认的添加方式
     */
    @Test
    public void testC(){
        Customer customer = new Customer();
        customer.setCustName("花花");
        customer.setCustAdress("湖南");
        customerRepository.save(customer);

    }


}
