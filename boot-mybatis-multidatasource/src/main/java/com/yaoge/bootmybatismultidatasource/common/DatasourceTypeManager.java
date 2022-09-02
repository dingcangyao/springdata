package com.yaoge.bootmybatismultidatasource.common;

/**
 * create by yaoge
 * 2022/9/2 15:13
 */
public class DatasourceTypeManager {
    //存放当前数据源是哪一个
    private final static ThreadLocal<DBTypeEnum> DB_HOLDER=new ThreadLocal<>();

    public static DBTypeEnum getDatasource(){
        return DB_HOLDER.get();
    }

    public static void setDatasource(DBTypeEnum dbType){
        DB_HOLDER.set(dbType);
    }

    public static void resetDatasource(){
        DB_HOLDER.set(DBTypeEnum.ADB);
    }


}
