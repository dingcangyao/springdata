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
public class PrimaryDataSourceConfig {

    /**
     *  扫描  spring.datasource.primary 开头的配置信息
     * @return
     */
    @Primary
    @Bean(name = "primaryDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSourceProperties dataSourceProperties(){

        return  new DataSourceProperties();
    }

    /**
     * 创建数据源对象
     * @param dataSourceProperties  注入名为primaryDataSourceProperties的bean
     * @return
     */
    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource dataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties dataSourceProperties){

        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
    @Primary
    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
