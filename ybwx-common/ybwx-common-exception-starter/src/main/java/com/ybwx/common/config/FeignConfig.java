package com.ybwx.common.config;

import com.ybwx.common.exception.FeignExceptionErrorDecode;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * global feign configuration
 */
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignExceptionErrorDecode();
    }

}
