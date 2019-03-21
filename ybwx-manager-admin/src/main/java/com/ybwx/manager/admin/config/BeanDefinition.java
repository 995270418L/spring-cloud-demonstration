package com.ybwx.manager.admin.config;

import com.ybwx.common.mysql.cache.ObjectCacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class BeanDefinition {

    @Value("${spring.redis.key-prefix}")
    private String keyPrefix;

    @Bean
    public ObjectCacher objectCacher(RedisTemplate redisTemplate){
        return new ObjectCacher(redisTemplate, keyPrefix);
    }

}
