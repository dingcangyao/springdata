package com.yaoge.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by yaoge
 * 2022/9/1 11:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor//都是 lombok的注解
public class People {
    private Integer id;
    private String name;
    private Double money;
}