package com.ybwx.common.config;

import com.ybwx.common.exception.FeignExceptionErrorDecode;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * global feign configuration
 */
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignExceptionErrorDecode();
    }

}
