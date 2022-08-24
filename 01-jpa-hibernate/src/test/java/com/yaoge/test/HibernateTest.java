package com.yaoge.test;

import com.yaoge.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

/**
 * create by yaoge
 * 2022/8/23 18:02
 * 测试 hibernate
 */
public class HibernateTest {

    //会话工厂用来创建和数据库的会话 称为session
    private Session session;
    private Transaction transaction;


    @Before
    public void init(){
        //就是获取sessionFactory的方式和原生 hibernate 不一致，这是提供的JPA规范
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        //2.格局服务注册创建一个元数据资源集，同时构建元数据并生成应用一般唯一的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
         session = sessionFactory.openSession();
        transaction= session.beginTransaction();
    }


    /**
     * 简单测试一下保存
     */
    @Test
    public void testC(){

            Customer customer = new Customer();

            customer.setCustName("huahua");
            customer.setCustAdress("菲律宾");
            Serializable save = session.save(customer);//返回的是 序列号
            System.out.println(save);

    }
    /**
     * c测试一下查询
     */
    @Test
    public void testR(){

        /**
         * 查询的方式
         * get
         * find
         * load 延时加载，用的时候才会发送SQL语句
         */
        Customer customer = session.load(Customer.class, 1L);
        System.out.println("===============");
        System.out.println(customer);

    }
    /**
     *
     * 测试删除
     */
    @Test
    public void testD(){

        Customer customer = new Customer();
        customer.setCustId(1L);
        session.remove(customer);

    }
    /**
     *
     * 测试更新
     */
    @Test
    public void testU(){

        Customer customer = new Customer();
        customer.setCustId(1L);
        customer.setCustName("新垣结衣");
        session.merge(customer);

    }
    //操作其实和hibernate的相关操作差不多
    @After
    public void destroy(){
        transaction.commit();
        session.close();
    }
}
