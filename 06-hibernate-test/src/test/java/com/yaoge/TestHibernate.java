package com.yaoge;

import com.yaoge.hibernate.pojo.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * create by yaoge
 * 2022/9/1 15:53
 */

public class TestHibernate {


    @Test
    public void testR(){

        //加载hibernate 全局配置文件 不指定文件位置，就是默认 resources：hibernate.cfg.xml
        Configuration configure = new Configuration().configure();

        //创建SessionFactory
        SessionFactory sessionFactory = configure.buildSessionFactory();

        //创建Session 用来操作数据库
        Session session = sessionFactory.openSession();

        //开启事务，并且获取事务器
        Transaction transaction = session.beginTransaction();

        People people = session.get(People.class, 9);
        System.out.println(people);
        transaction.commit();
        session.close();
    }
}
