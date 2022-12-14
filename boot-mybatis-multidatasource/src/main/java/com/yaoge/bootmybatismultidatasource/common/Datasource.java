package com.yaoge.bootmybatismultidatasource.common;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})//表示可以在方法和类上添加注解
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Datasource {

    DBTypeEnum type() default DBTypeEnum.ADB;//添加一个属性，就是数据源类型，默认就是ADB
}
