package com.ybwx.stock;

import com.ybwx.common.util.BootstrapLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication sp = new SpringApplication(StockApplication.class);
        Environment environment = sp.run(args).getEnvironment();
        BootstrapLogging.logging(environment);
    }

}
