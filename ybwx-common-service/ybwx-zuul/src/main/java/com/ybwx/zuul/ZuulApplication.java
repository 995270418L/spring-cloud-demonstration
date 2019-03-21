package com.ybwx.zuul;

import com.ybwx.common.util.BootstrapLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication sp = new SpringApplication(ZuulApplication.class);
        Environment environment = sp.run(args).getEnvironment();
        BootstrapLogging.logging(environment);
    }

}
