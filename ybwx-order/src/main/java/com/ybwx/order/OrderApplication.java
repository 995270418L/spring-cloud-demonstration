package com.ybwx.order;

import com.ybwx.common.config.FeignConfig;
import com.ybwx.common.util.BootstrapLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ybwx.order.feign"}, defaultConfiguration = FeignConfig.class)
public class OrderApplication {

    public static void main(String[] args){
        SpringApplication sp = new SpringApplication(OrderApplication.class);
        Environment environment = sp.run(args).getEnvironment();
        BootstrapLogging.logging(environment);
    }


}
