package com.yaoge.springboothibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootHibernateApplication.class, args);
    }

}
