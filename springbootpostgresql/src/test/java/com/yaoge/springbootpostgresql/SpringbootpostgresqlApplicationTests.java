package com.yaoge.springbootpostgresql;

import com.yaoge.springbootpostgresql.pojo.People;
import com.yaoge.springbootpostgresql.repository.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootpostgresqlApplicationTests {


    @Autowired
    private PeopleRepository peopleRepository;
    @Test
    void contextLoads() {

        Iterable<People> all = peopleRepository.findAll();
        all.forEach(System.out::println);
    }

}
