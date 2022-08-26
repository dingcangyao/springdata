package com.yaoge.test;

import com.yaoge.config.SpringDataJpaConfiguration;
import com.yaoge.entity.Customer;
import com.yaoge.repository.CustomerMethodNameRepository;
import com.yaoge.repository.CustomerQBERepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * create by yaoge
 * 2022/8/24 18:07
 *
 * Query By Exaple 测试
 */
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)//java config 配置方式
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(value = false)//test方法中会对事务进行默认回滚，要设置一下
public class QBETest {

    @Autowired
    CustomerQBERepository customerRepository;

    /**
     * 简单示例，客户名称、客户地址动态查询
     */
    @Test
    public void test01(){

        //查询条件
        Customer customer = new Customer();
        customer.setCustName("李四");

        //通过Exaple 构建查询条件
        Example<Customer> example = Example.of(customer);

        Iterable<Customer> all = customerRepository.findAll(example);
        System.out.println(all);

    }
    /**
     * 简单示例，通过匹配器（Matcher） 进行条件限制
     * 还可以设置String 的开头匹配，或者结尾匹配，或者正则匹配
     * 是对所有的条件进行字符串匹配
     * 想要单独配置 就需要 使用  withMatcher用来单独设置
     */
    @Test
    public void test02(){

        Customer customer = new Customer();
        customer.setCustName("yaoayo");
        customer.setCustAdress("beijing");

        ExampleMatcher custNameMatcher = ExampleMatcher.matching().withIgnorePaths("custName");//可以屏蔽某个列


        Example<Customer> example = Example.of(customer, custNameMatcher);

        Iterable<Customer> all = customerRepository.findAll(example);
        System.out.println(all);


    }


}
