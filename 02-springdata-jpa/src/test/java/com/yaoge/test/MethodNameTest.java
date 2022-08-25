package com.yaoge.test;

import com.yaoge.config.SpringDataJpaConfiguration;
import com.yaoge.entity.Customer;
import com.yaoge.repository.CustomerMethodNameRepository;
import com.yaoge.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by yaoge
 * 2022/8/24 18:07
 *
 * 测试方法名自定义查询
 * 都有相应的规则和示例，具体使用的时候可以再查询
 */
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)//java config 配置方式
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(value = false)//test方法中会对事务进行默认回滚，要设置一下
public class MethodNameTest {

    @Autowired
    CustomerMethodNameRepository customerRepository;

    /**
     * 测试查询根据名字
     */
    @Test
    public void testR(){

        List<Customer> cu = customerRepository.findByCustName("翁美玲");
        System.out.println(cu);

    }
    /**
     * 测试查询根据名字
     */
    @Test
    public void test02(){

        boolean exists = customerRepository.existsByCustName("yaoge");
        System.out.println(exists);


    }


}
