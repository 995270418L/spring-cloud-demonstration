package com.ybwx.multidata.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MybatisAOP {

    @Before("execution(* com.ybwx.common.mysql.common.impl.*.insert*(..)) || execution(* com.ybwx.common.mysql.common.impl.*.update*(..)) || " +
            "execution(* com.ybwx.common.mysql.common.impl.*.delete*(..))")
    public void setMasterDataSource(){
        log.info("dataSource切换到：master");
        DynamicDataSource.master();
    }

    @Before("execution(* com.ybwx.common.mysql.common.impl.*.find*(..)) || execution(* com.ybwx.common.mysql.common.impl.*.select*(..))")
    public void setSlaveDataSource(){
        log.info("dataSource切换到：slave");
        DynamicDataSource.slave();
    }

}
