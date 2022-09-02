package com.yaoge.bootmybatismultidatasource.dao;


import com.yaoge.bootmybatismultidatasource.common.DBTypeEnum;
import com.yaoge.bootmybatismultidatasource.common.Datasource;
import com.yaoge.bootmybatismultidatasource.pojo.Man;
import com.yaoge.bootmybatismultidatasource.pojo.Woman;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManMapper {

    @Datasource(type = DBTypeEnum.BDB)
    public List<Man> selectPeoples();

    public int insertPeople();
}
