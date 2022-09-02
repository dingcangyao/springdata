package com.yaoge.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaoge.dao.PeopleMapper;
import com.yaoge.pojo.People;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * create by yaoge
 * 2022/8/31 17:38
 */
public class TestOne {

    @Test
    public void  testIn() throws IOException {

        //读取Mybatis 配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //创建 SqlSessionFactoryBuilder 对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //通过核心配置文件所对应的字节输入流创建工厂类 SqlSessionFactory 生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //创建SqlSession对象，此时通过SqlSession 对象所操作的sq都必须手动提交或回滚事务
        // SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建sqlsession对象，此时通过SqlSession 对象操作的sql 都会自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //通过dialing模式创建 PeopelMapper 接口的代理实现类对象
        PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);
        //调用PeopleMapper中的方法，就可以根据 PeopleMapper的全类名匹配元素文件，通过调用的方法名匹配映射文件中SQL表亲啊，并且执行标签中的SQL文件
        int i = mapper.insertUser();
        System.out.println("结果"+i);
    }

    /**
     * 测试 分页
     * @throws IOException
     */
    @Test
    public void  testPage() throws IOException {

        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);

         PageHelper.startPage(2, 4);//开启分页  指定 当前页数 和 页中 记录数目
        List<People> peoples = mapper.findPeoples();
        PageInfo<People> peoplePageInfo = new PageInfo<>(peoples, 2);//获取分页详细信息  参数一：查询的数据  参数二 ：显示的前后导航数

        System.out.println(peoples);

    }
}
