package com.ybwx.order.security;

import lombok.extern.slf4j.Slf4j;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Slf4j
@Configuration
@EnableResourceServer   // OAuth2AuthenticationProcessingFilter
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedissonConnectionFactory redissonConnectionFactory;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/**").authenticated()
                    .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Primary
//    public RemoteTokenServices tokenServices(){
//        final RemoteTokenServices tokenService = new RemoteTokenServices();
//        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/uaa/oauth/check_token");
//        tokenService.setClientId("client_1");
//        tokenService.setClientSecret("client_1");
//        return tokenService;
//    }

    @Bean
    @Primary
    public ResourceServerTokenServices resourceServerTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        RedisTokenStore redisTokenStore = new RedisTokenStore(redissonConnectionFactory);
        redisTokenStore.setPrefix("user-auth:");
        tokenServices.setTokenStore(redisTokenStore);
        tokenServices.setSupportRefreshToken(true);
        //        defaultTokenServices.setClientDetailsService(new Oauth2ClientDetailService());
        tokenServices.setAccessTokenValiditySeconds(3600);
        return tokenServices;
    }

}
