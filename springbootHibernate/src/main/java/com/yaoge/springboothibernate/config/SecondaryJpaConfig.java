package com.yaoge.springboothibernate.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * create by yaoge
 * 2022/9/1 18:34
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = SecondaryJpaConfig.REPOSITORY_PACKAGE,
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryJpaConfig {
    static final String REPOSITORY_PACKAGE = "com.yaoge.springboothibernate.repository.secondary";
    private static final String ENTITY_PACKAGE = "com.yaoge.springboothibernate.pojo.secondary";


    /**
     * 扫描spring.jpa.secondary
     *
     * @return jpa配置信息
     */
    @Bean(name = "secondaryJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa.secondary")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    /**
     * 获取次库实体管理工厂对象
     *
     * @param secondaryDataSource 注入名为secondaryDataSource的数据源
     * @param jpaProperties       注入名为secondaryJpaProperties的jpa配置信息
     * @param builder             注入EntityManagerFactoryBuilder
     * @return 实体管理工厂对象
     */
    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("secondaryDataSource") DataSource secondaryDataSource,
            @Qualifier("secondaryJpaProperties") JpaProperties jpaProperties,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                // 设置数据源
                .dataSource(secondaryDataSource)
                // 设置jpa配置
                .properties(jpaProperties.getProperties())
                // 设置实体包名
                .packages(ENTITY_PACKAGE)
                // 设置持久化单元名，用于@PersistenceContext注解获取EntityManager时指定数据源
                .persistenceUnit("secondaryPersistenceUnit").build();
    }

    /**
     * 获取实体管理对象
     *
     * @param factory 注入名为secondaryEntityManagerFactory的bean
     * @return 实体管理对象
     */
    @Bean(name = "secondaryEntityManager")
    public EntityManager entityManager(@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    /**
     * 获取主库事务管理对象
     *
     * @param factory 注入名为secondaryEntityManagerFactory的bean
     * @return 事务管理对象
     */
    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
