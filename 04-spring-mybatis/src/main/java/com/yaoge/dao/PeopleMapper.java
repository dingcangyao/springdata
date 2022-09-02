package com.yaoge.dao;

import com.yaoge.pojo.People;

import java.util.List;

public interface PeopleMapper {

    public List<People> selectPeoples();

    public int insertPeople();
}
