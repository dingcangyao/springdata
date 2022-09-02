package com.yaoge.bootmybatismultidatasource.dao;


import com.yaoge.bootmybatismultidatasource.common.DBTypeEnum;
import com.yaoge.bootmybatismultidatasource.common.Datasource;
import com.yaoge.bootmybatismultidatasource.pojo.People;
import com.yaoge.bootmybatismultidatasource.pojo.Woman;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WomanMapper {

    @Datasource(type = DBTypeEnum.ADB)
    public List<Woman> selectPeoples();

    public int insertPeople();
}
