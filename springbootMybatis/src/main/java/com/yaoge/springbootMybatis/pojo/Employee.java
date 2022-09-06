package com.yaoge.springbootMybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by yaoge
 * 2022/8/31 17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {
    private Integer id;
    private String name;
    private Double money;
}
