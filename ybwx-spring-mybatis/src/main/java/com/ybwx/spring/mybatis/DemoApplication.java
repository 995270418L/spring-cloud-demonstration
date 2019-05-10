package com.ybwx.spring.mybatis;

import com.ybwx.spring.mybatis.config.HikariDataSourceProxy;
import com.ybwx.spring.mybatis.config.SpringService;
import com.ybwx.spring.mybatis.service.UserService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private SpringService springSerivce;

    public static void main(String[] args) {
        SpringApplication sp = new SpringApplication(DemoApplication.class);
        sp.run(args);
    }

    @GetMapping("/inject")
    public String inject() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://mysql.res.steve.com:3306/demo?allowPublicKeyRetrieval=true&useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8"); //数据源
        config.setUsername("root"); //用户名
        config.setPassword("root"); //密码
        config.setMaximumPoolSize(5);
        config.setLeakDetectionThreshold(10000);
//        HikariDataSource hikariDataSource = new HikariDataSource(config);
        springSerivce.injectBean("customDataSource", BeanDefinitionBuilder.rootBeanDefinition(HikariDataSource.class).addConstructorArgValue(config).getBeanDefinition());
//        DataSource dataSource = springSerivce.getBean(DataSource.class);
//        if(dataSource instanceof HikariDataSourceProxy){
//            HikariDataSourceProxy hikariDataSourceProxy = (HikariDataSourceProxy) dataSource;
//            hikariDataSourceProxy.setHikariDataSource(hikariDataSource);
//        }
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        springSerivce.injectBean(sqlSessionFactory);
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        configurer.setBasePackage("com.ybwx.spring.mybatis.mapper");
//        springSerivce.injectBean(configurer);
        return "OK";
    }

    @GetMapping("/obtain")
    public String obtain(){
        SqlSessionFactory sqlSessionFactory = springSerivce.getBean(SqlSessionFactory.class);
        if(sqlSessionFactory != null){
            return sqlSessionFactory.toString();
        }else{
            return "null";
        }
    }

    @GetMapping("/index")
    public String user(){
       return userService.findById(1L).toString();
    }

    @GetMapping("/insert")
    public void insert(){

        new Thread(){
            @Override
            public void run() {
                for(;;) {
                    userService.save();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return;
    }
    @Override
    public void run(String... args) throws Exception {
//        for(;;) {
//            System.out.println(userService.findById(1L));
//            Thread.sleep(2000);
//        }
    }
}
