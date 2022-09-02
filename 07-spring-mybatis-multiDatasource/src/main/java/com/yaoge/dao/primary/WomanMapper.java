package com.yaoge.dao.primary;

import com.yaoge.pojo.People;
import com.yaoge.pojo.primary.Woman;

import java.util.List;

public interface WomanMapper {

    public List<Woman> selectPeoples();

    public int insertPeople();
}
