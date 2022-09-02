package com.yaoge.bootmybatismultidatasource.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.yaoge.bootmybatismultidatasource.common.DBTypeEnum;
import com.yaoge.bootmybatismultidatasource.common.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * create by yaoge
 * 2022/9/2 15:39
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DynamicConfiguration {

    /**
     * 创建数据源A
     * @return
     */
    @Bean(name = "dataSourceA")
    public DataSource dataSourceA(){

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        return druidDataSource;
    }

    /**
     * 创建数据源B
     * @return
     */
    @Bean(name = "dataSourceB")
    public DataSource dataSourceB(){

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        return druidDataSource;
    }

    /**
     * 创建动态数据源
     * @param dataSourceA
     * @param dataSourceB
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("dataSourceA") DataSource dataSourceA, @Qualifier("dataSourceB") DataSource dataSourceB){


        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSourceA);//指定默认数据源
        Map map = new HashMap<>();

        map.put(DBTypeEnum.ADB,dataSourceA);
        map.put(DBTypeEnum.BDB,dataSourceB);
        dynamicDataSource.setTargetDataSources(map);//放入 数据源映射，到时候，就会根据  里面我们重写的方法返回的值作为key找到对应的value作为datasource

        return dynamicDataSource;
    }

    /**
     * 创建对应的sqlSessionFactory
     * @param dataSource
     * @param applicationContext
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);//设置动态数据源
        sqlSessionFactory.setTypeAliasesPackage("com.yaoge.bootmybatismultidatasource.pojo");//指定别名包
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/*Mapper.xml"));//指定mapper文件所在位置

        return sqlSessionFactory.getObject();

    }

    /**
     * 创建事务管理器
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManager")
    public TransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource){

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
