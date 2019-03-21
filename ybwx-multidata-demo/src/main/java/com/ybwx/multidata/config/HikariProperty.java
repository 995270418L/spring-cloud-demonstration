package com.ybwx.multidata.config;

import org.springframework.core.env.Environment;

public class HikariProperty {

    private String prefix;
    private Environment environment;


    public HikariProperty(Environment environment, String prefix){
        this.environment = environment;
        this.prefix = prefix;
    }

    public String getUrl() {
        return environment.getProperty(prefix + ".url" );
    }

    public Long getConnectionTimeout() {
        return Long.valueOf(environment.getProperty(prefix + ".connectionTimeout"));

    }

    public Long getMaxLifetime() {
        return Long.valueOf(environment.getProperty(prefix + ".maxLifetime"));
    }

    public Integer getMaximumPoolSize() {
        return Integer.valueOf(environment.getProperty(prefix + ".maximumPoolSize"));
    }

    public String getUsername() {
        return environment.getProperty(prefix + ".username");
    }

    public String getPassword() {
        return environment.getProperty(prefix + ".password");
    }

    public Boolean getCachePrepStmts() {
        return Boolean.valueOf(environment.getProperty(prefix + ".cachePrepStmts"));
    }

    public Boolean getUseServerPrepStmts() {
        return Boolean.valueOf(environment.getProperty(prefix + ".useServerPrepStmts"));
    }

    public Boolean getUseLocalSessionState() {
        return Boolean.valueOf(environment.getProperty(prefix + ".useLocalSessionState"));
    }

    public Boolean getUseLocalTransactionState() {
        return Boolean.valueOf(environment.getProperty(prefix + ".useLocalTransactionState"));
    }

    public Boolean getRewriteBatchedStatements() {
        return Boolean.valueOf(environment.getProperty(prefix + ".rewriteBatchedStatements"));
    }

    public Boolean getCacheResultSetMetadata() {
        return Boolean.valueOf(environment.getProperty(prefix + ".cacheResultSetMetadata"));
    }

    public Boolean getCacheServerConfiguration() {
        return Boolean.valueOf(environment.getProperty(prefix + ".cacheServerConfiguration"));
    }

    public Boolean getElideSetAutoCommits() {
        return Boolean.valueOf(environment.getProperty(prefix + ".elideSetAutoCommits"));
    }

    public Boolean getMaintainTimeStats() {
        return Boolean.valueOf(environment.getProperty(prefix + ".maintainTimeStats"));
    }

    public Integer getPrepStmtCacheSize() {
        return Integer.valueOf(environment.getProperty(prefix + ".prepStmtCacheSqlLimit"));
    }

    public Integer getPrepStmtCacheSqlLimit() {
        return Integer.valueOf(environment.getProperty(prefix + ".prepStmtCacheSize"));
    }

}
