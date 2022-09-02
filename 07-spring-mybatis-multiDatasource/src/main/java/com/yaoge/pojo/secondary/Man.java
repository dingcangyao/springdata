package com.yaoge.pojo.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by yaoge
 * 2022/8/31 17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor//都是 lombok的注解
public class Man {
    private Integer id;
    private String name;
    private Double money;
}
