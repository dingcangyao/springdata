package com.yaoge.dao;

import com.yaoge.pojo.People;

import java.util.List;

/**
 * mapper 类就是为了映射对 数据库的操作
 * 方法就是操作
 * 每一个方法和 mapper.xml文件对应
 */
public interface PeopleMapper {

    /**
     * 添加用户信息
     *
     */

    int insertUser();

    List<People> findPeoples();
}
