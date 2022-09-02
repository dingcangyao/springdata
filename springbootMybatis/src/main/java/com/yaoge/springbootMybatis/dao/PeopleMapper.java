package com.yaoge.springbootMybatis.dao;


import com.yaoge.springbootMybatis.pojo.People;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleMapper {

    public List<People> selectPeoples();

    public int insertPeople();
}
