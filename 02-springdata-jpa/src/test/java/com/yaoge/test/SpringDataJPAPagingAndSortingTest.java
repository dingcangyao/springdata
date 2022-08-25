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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * create by yaoge
 * 2022/8/24 18:07
 */
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)//java config 配置方式
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJPAPagingAndSortingTest {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * 演示分页
     */
    @Test
    public void testPaging(){
        Page<Customer> all = customerRepository.findAll(PageRequest.of(0, 3));
        all.forEach(customer -> {
            System.out.println(customer);
        });
        System.out.println(all.getTotalElements());//总的元素数量
        System.out.println(all.getTotalPages());//总页数
        System.out.println(all.getNumber());//当前的页数 从0开始

    }
    /**
     * 演示排序
     */
    @Test
    public void testSort(){

        Sort custId = Sort.by("custId").descending();

        Iterable<Customer> all = customerRepository.findAll(custId);
        all.forEach(customer -> {
            System.out.println(customer);
        });

    }
    /**
     * 演示类型安全方式排序
     * 因为是使用方法来获取属性，所以后面属性名换了也可以使用
     */
    @Test
    public void testSorttypeSafe(){

        Sort.TypedSort<Customer> sortType=Sort.sort(Customer.class);

        Sort descending = sortType.by(Customer::getCustId).descending();

        Iterable<Customer> all = customerRepository.findAll(descending);
        all.forEach(customer -> System.out.println(customer));;



    }
}
