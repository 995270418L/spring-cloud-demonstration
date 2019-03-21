package com.ybwx.multidata.config;

import com.ybwx.multidata.enums.DatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    /**
     * 从 targetDataSources 中获取指定key的value值
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public static void master(){
        contextHolder.set(DatabaseType.MASTER);
    }

    public static void slave(){
        contextHolder.set(DatabaseType.SLAVE);
    }

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getType(){
        return contextHolder.get();
    }
}
