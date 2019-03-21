package com.ybwx.multidata.config;

import com.ybwx.multidata.enums.DatabaseType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration implements EnvironmentAware {

    private HikariProperty masterHikariProperty = null;
    private HikariProperty slaveHikariProperty = null;

    @Override
    public void setEnvironment(Environment environment) {
        this.masterHikariProperty = new HikariProperty(environment, "spring.datasource.master");
        this.slaveHikariProperty = new HikariProperty(environment, "spring.datasource.slave");
    }

    public DataSource master(){
        return set(masterHikariProperty);
    }

    public DataSource slave(){
        return set(slaveHikariProperty);
    }

    private DataSource set(HikariProperty hikariProperty) {
        HikariConfig config = new HikariConfig ();
        config.setJdbcUrl(hikariProperty.getUrl()); //数据源
        config.setUsername(hikariProperty.getUsername()); //用户名
        config.setPassword(hikariProperty.getPassword()); //密码
        config.addDataSourceProperty("cachePrepStmts", hikariProperty.getCachePrepStmts()); //是否自定义配置，为true时下面两个参数才生效
        config.addDataSourceProperty("prepStmtCacheSize", hikariProperty.getPrepStmtCacheSize()); //连接池大小默认25，官方推荐250-500
        config.addDataSourceProperty("prepStmtCacheSqlLimit", hikariProperty.getPrepStmtCacheSqlLimit()); //单条语句最大长度默认256，官方推荐2048
        config.addDataSourceProperty("useServerPrepStmts", hikariProperty.getUseServerPrepStmts()); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
        config.addDataSourceProperty("useLocalSessionState", hikariProperty.getUseLocalSessionState());
        config.addDataSourceProperty("useLocalTransactionState", hikariProperty.getUseLocalTransactionState());
        config.addDataSourceProperty("rewriteBatchedStatements", hikariProperty.getRewriteBatchedStatements());
        config.addDataSourceProperty("cacheResultSetMetadata", hikariProperty.getCacheResultSetMetadata());
        config.addDataSourceProperty("cacheServerConfiguration", hikariProperty.getCacheServerConfiguration());
        config.addDataSourceProperty("elideSetAutoCommits", hikariProperty.getElideSetAutoCommits());
        config.addDataSourceProperty("maintainTimeStats", hikariProperty.getMaintainTimeStats());
        return new HikariDataSource(config);
    }

    @Bean
    public DynamicDataSource dynamicDataSource(){
        DataSource master = master();
        DataSource slave = slave();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DatabaseType.MASTER, master);
        targetDataSources.put(DatabaseType.SLAVE, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(master);
        return dataSource;
    }
}
