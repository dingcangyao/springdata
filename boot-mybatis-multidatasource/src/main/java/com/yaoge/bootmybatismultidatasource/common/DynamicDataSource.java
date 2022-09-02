package com.yaoge.bootmybatismultidatasource.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * create by yaoge
 * 2022/9/2 15:17
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceTypeManager.getDatasource();
    }
}
