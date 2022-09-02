package com.yaoge.bootmybatismultidatasource.common;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Datasource {

    DBTypeEnum type() default DBTypeEnum.ADB;
}
