package com.yaoge.bootmybatismultidatasource.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * create by yaoge
 * 2022/9/2 15:17
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    /**
     * 重写这个方法，用来指定当前使用的数据源是哪一个
     * 这个返回的只是一个key
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceTypeManager.getDatasource();
    }
}
