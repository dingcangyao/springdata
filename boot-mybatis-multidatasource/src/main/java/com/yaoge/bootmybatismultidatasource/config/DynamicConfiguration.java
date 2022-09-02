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

    @Bean(name = "dataSourceA")
    public DataSource dataSourceA(){

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/hibernate?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        return druidDataSource;
    }
    @Bean(name = "dataSourceB")
    public DataSource dataSourceB(){

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        return druidDataSource;
    }
    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("dataSourceA") DataSource dataSourceA, @Qualifier("dataSourceB") DataSource dataSourceB){


        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSourceA);
        Map map = new HashMap<>();

        map.put(DBTypeEnum.ADB,dataSourceA);
        map.put(DBTypeEnum.BDB,dataSourceB);
        dynamicDataSource.setTargetDataSources(map);

        return dynamicDataSource;
    }
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.yaoge.bootmybatismultidatasource.pojo");
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:mappers/*Mapper.xml"));
        return sqlSessionFactory.getObject();

    }
    @Bean(name = "transactionManager")
    public TransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource){

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
