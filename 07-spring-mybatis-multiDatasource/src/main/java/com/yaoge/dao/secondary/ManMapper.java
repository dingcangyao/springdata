package com.yaoge.dao.secondary;

import com.yaoge.pojo.People;
import com.yaoge.pojo.secondary.Man;

import java.util.List;

public interface ManMapper {

    public List<Man> selectPeoples();

    public int insertPeople();
}
