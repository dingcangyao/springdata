package com.yaoge.springboothibernate.repository.primary;

import com.yaoge.springboothibernate.pojo.People;
import com.yaoge.springboothibernate.pojo.primary.Woman;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WomanRepository extends CrudRepository<Woman,Integer> {
}
