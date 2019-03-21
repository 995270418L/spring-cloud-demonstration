package com.ybwx.multidata.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@Slf4j
public class MybatisConfiguration {

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    @Bean
    public MapperScannerConfigurer scannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("com.ybwx.multidata.mapper");
        return configurer;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        String[] locations = {"classpath*:/mybatis/*Mapper.xml"};
        sqlSessionFactoryBean.setMapperLocations(Stream.of(Optional.ofNullable(locations).orElse(new String[0])).flatMap(e -> Stream.of(getResources(e))).toArray(Resource[]::new));
        try{
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            log.info("sql session bean 初始化成功: {}", sqlSessionFactory.getConfiguration());
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("SqlSessionFactory 初始化失败");
        return null;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }



    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException e) {
            return new Resource[0];
        }
    }
}
