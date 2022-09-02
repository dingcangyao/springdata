package com.yaoge.springboothibernate;

import com.yaoge.springboothibernate.pojo.primary.Woman;
import com.yaoge.springboothibernate.repository.PeopleRepository;
import com.yaoge.springboothibernate.repository.primary.WomanRepository;
import com.yaoge.springboothibernate.repository.secondary.ManRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootTest
class SpringbootHibernateApplicationTests {



    @Autowired
    WomanRepository womanRepository;
    @Autowired
    ManRepository manRepository;
    @Test
    void contextLoads() {


        Iterable all= womanRepository.findAll();
        all.forEach(System.out::println);

        all= manRepository.findAll();
        all.forEach(System.out::println);

    }

}
