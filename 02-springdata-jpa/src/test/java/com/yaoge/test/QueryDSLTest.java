package com.yaoge.test;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yaoge.config.SpringDataJpaConfiguration;
import com.yaoge.entity.Customer;
import com.yaoge.entity.QCustomer;
import com.yaoge.repository.CustomerQueryDSLRepository;
import com.yaoge.repository.CustomerSpecificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * create by yaoge
 * 2022/8/24 18:07
 *  QueryDSL广
 */
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)//java config 配置方式
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(value = false)//test方法中会对事务进行默认回滚，要设置一下
public class QueryDSLTest {

    @Autowired
    CustomerQueryDSLRepository customerRepository;

    @PersistenceContext//用 AutoWired 会有线程安全问题
    EntityManager em;

    /**
     * 简单示例,操作相比 specification
     */
    @Test
    public void test01(){

        //获取Q类，操作都是使用Q类使用
        QCustomer qCustomer = QCustomer.customer;

        BooleanExpression eq = qCustomer.custId.in(7L,8L);
        Iterable<Customer> all = customerRepository.findAll(eq);
        all.forEach(item-> System.out.println(item));

    }
    /**
     * 前端发送条件查询
     */
    @Test
    public void test02(){

        Customer params = new Customer();
        params.setCustId(8L);
        params.setCustName("宋庆龄,张作霖");
        //获取Q类，操作都是使用Q类使用
        QCustomer qCustomer = QCustomer.customer;

        //初始条件
        BooleanExpression expression = qCustomer.custId.isNotNull().or(qCustomer.custId.isNull());

        expression =params.getCustId()>0L?expression.and(qCustomer.custId.gt(params.getCustId())):expression;
        expression =params.getCustName()!=null?expression.and(qCustomer.custName.in(params.getCustName().split(","))):expression;
        Iterable<Customer> all = customerRepository.findAll(expression);


        all.forEach(item-> System.out.println(item));

    }
    /**
     * 自定义列查询，分组,聚合
     * 需要使用原生态的方式（specification也是一样的）
     * 通过 repository 进行查询，都是固定的，不能修改
     */
    @Test
    public void test03(){

        QCustomer customer = QCustomer.customer;

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);//用于构建查询条件

        //Tuple 类型就是当你类型不确定的时候，帮你构建出来的数据类型
        //应该就是可以兼容很多数据的一个Map
        JPAQuery<Tuple> tupleJPAQuery = jpaQueryFactory.select(customer.custId, customer.custName)
                .from(customer)
                .where(customer.custId.gt(8L))
                .orderBy(customer.custId.desc());
        List<Tuple> fetch = tupleJPAQuery.fetch();
        fetch.forEach(item->{
            System.out.print(item.get(customer.custId));
            System.out.println(item.get(customer.custName));
        });


    }


}
