package com.yaoge.springboothibernate.pojo.secondary;

import lombok.Data;

import javax.persistence.*;

/**
 * create by yaoge
 * 2022/9/1 15:38
 */
@Data
@Entity
@Table(name = "man")
public class Man {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "money")
    private Double money;

}
