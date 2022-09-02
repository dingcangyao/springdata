package com.yaoge.bootmybatismultidatasource;

import com.yaoge.bootmybatismultidatasource.common.DBTypeEnum;
import com.yaoge.bootmybatismultidatasource.common.Datasource;
import com.yaoge.bootmybatismultidatasource.dao.ManMapper;
import com.yaoge.bootmybatismultidatasource.dao.PeopleMapper;
import com.yaoge.bootmybatismultidatasource.dao.WomanMapper;
import com.yaoge.bootmybatismultidatasource.pojo.Man;
import com.yaoge.bootmybatismultidatasource.pojo.People;
import com.yaoge.bootmybatismultidatasource.pojo.Woman;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@MapperScan("com.yaoge.bootmybatismultidatasource.dao")
@SpringBootTest
class BootMybatisMultidatasourceApplicationTests {

    @Autowired
    private PeopleMapper peopleMapper;
    @Autowired
    private WomanMapper womanMapper;
    @Autowired
    private ManMapper manMapper;

    @Test
    @Datasource(type = DBTypeEnum.ADB)
    void contextLoads() {

        List<People> people = peopleMapper.selectPeoples();
        people.forEach(System.out::println);
    }

    @Datasource(type = DBTypeEnum.ADB)
    @Test
    public void testDdynamic(){

        List<Woman> women = womanMapper.selectPeoples();
        women.forEach(System.out::println);

        List<Man> men = manMapper.selectPeoples();
        men.forEach(System.out::println);


    }


    @Test
    public void testBdynamic(){//DataSource 注解好像加载测试方法上没有用



        List<Man> men = manMapper.selectPeoples();
        men.forEach(System.out::println);

    }


}
