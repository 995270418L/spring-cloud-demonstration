package com.ybwx.manager.admin;

import com.ybwx.common.util.BootstrapLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication sp = new SpringApplication(AdminApplication.class);
        Environment environment = sp.run(args).getEnvironment();
        BootstrapLogging.logging(environment);
    }

}
