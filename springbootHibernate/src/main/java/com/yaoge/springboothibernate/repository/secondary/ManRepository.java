package com.yaoge.springboothibernate.repository.secondary;

import com.yaoge.springboothibernate.pojo.People;
import com.yaoge.springboothibernate.pojo.secondary.Man;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManRepository extends CrudRepository<Man,Integer> {
}
