package com.yaoge.springboothibernate.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * create by yaoge
 * 2022/9/1 18:23
 */
@Configuration
public class SecondaryDataSourceConfig {

    /**
     *  扫描  spring.datasource.secondary 开头的配置信息
     * @return
     */
    @Bean(name = "secondaryDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSourceProperties dataSourceProperties(){

        return  new DataSourceProperties();
    }

    /**
     * 获取次数据源对象
     *
     * @param dataSourceProperties 注入名为secondaryDataSourceProperties的bean
     * @return 数据源对象
     */
    @Bean(name = "secondaryDataSource")
    public DataSource dataSource(@Qualifier("secondaryDataSourceProperties") DataSourceProperties dataSourceProperties){

        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 该方法仅在需要使用JdbcTemplate对象时选用
     *
     * @param dataSource 注入名为secondaryDataSource的bean
     * @return 数据源JdbcTemplate对象
     */
    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
