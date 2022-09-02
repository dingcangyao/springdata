package com.yaoge.bootmybatismultidatasource.dao;


import com.yaoge.bootmybatismultidatasource.pojo.People;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleMapper {

    public List<People> selectPeoples();

    public int insertPeople();
}
