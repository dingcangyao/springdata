package com.yaoge.test;

import com.yaoge.config.SpringDataJpaConfiguration;
import com.yaoge.entity.Customer;
import com.yaoge.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * create by yaoge
 * 2022/8/24 18:07
 *
 * 测试jpql
 */
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)//java config 配置方式
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(value = false)//test方法中会对事务进行默认回滚，要设置一下
public class JpqlTest {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * 演示自定义查询
     */
    @Test
    public void testR(){

        Customer customer = customerRepository.findCustomerByCusName("翁美玲");
        System.out.println(customer);

    }
    /**
     * 演示原生SQL  自定义查询
     */
    @Test
    public void testRNative(){

        Customer customer = customerRepository.findCustomerByNative("翁美玲");
        System.out.println(customer);

    }
    /**
     * 演示更新
     */


    /**
     * 增删改 ：需要加上事务注解  @Transactional
     * 然后在respository 的方法中 加上@Modifying 标签
     */
    @Transactional
    @Test
    public void testU(){

        int count = customerRepository.updateCustomer("岳云鹏", 4L);
        System.out.println(count);

    }
    /**
     *测试删除语句
     */
    @Transactional//如果加上事务 ，test中的方法会默认回滚，设置rollback（flase）取消回滚
    @Test
    public void testD(){

        int count = customerRepository.deleteCustomer( 4L);
        System.out.println(count);

    }
    /**
     *测试特殊新增
     */
    @Transactional//如果加上事务 ，test中的方法会默认回滚，设置rollback（flase）取消回滚
    @Test
    public void testC(){

        int count = customerRepository.insertCustomer( 5L);
        System.out.println(count);

    }

}
