package com.ybwx.multidata;

import com.ybwx.common.util.BootstrapLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApplicationDemo {

    public static void main(String[] args) {
        SpringApplication sp = new SpringApplication(ApplicationDemo.class);
        Environment environment = sp.run(args).getEnvironment();
        BootstrapLogging.logging(environment);
    }

}
