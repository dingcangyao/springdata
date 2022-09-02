package com.yaoge.springboothibernate.repository;

import com.yaoge.springboothibernate.pojo.People;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends CrudRepository<People,Integer> {
}
