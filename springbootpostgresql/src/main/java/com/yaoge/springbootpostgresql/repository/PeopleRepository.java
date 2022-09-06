package com.yaoge.springbootpostgresql.repository;

import com.yaoge.springbootpostgresql.pojo.People;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
* create by yaoge
* 2022/9/5 15:01
*/
@Repository
public interface PeopleRepository extends CrudRepository<People,Integer> {
}
