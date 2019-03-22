package com.ybwx.uaa.config;

import com.ybwx.uaa.service.Oauth2UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务器配置
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedissonConnectionFactory redissonConnectionFactory;

    @Bean
    public RedisTokenStore redisTokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redissonConnectionFactory);
        redisTokenStore.setPrefix("user-auth:");
        return redisTokenStore;
    }

    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        //        defaultTokenServices.setClientDetailsService(new Oauth2ClientDetailService());
        tokenServices.setAccessTokenValiditySeconds(3600);
        return tokenServices;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .requestMatchers()
                    .antMatchers("/login/**", "/logout/**", "/oauth/**")
                    .and()
                .authorizeRequests()
                    .antMatchers("/login/**", "/logout/**").permitAll()
                    .antMatchers("/oauth/**").authenticated()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginProcessingUrl("/login");
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new Oauth2UserDetailService();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
