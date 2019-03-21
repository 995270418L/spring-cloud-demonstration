package com.ybwx.manager.admin.security.config;

import com.ybwx.manager.admin.security.RestAccessDeniedHanlder;
import com.ybwx.manager.admin.security.UrlAuthenticationEntryPoint;
import com.ybwx.manager.admin.security.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

/**
 * 这个注解就会自动将目前的类注册成filter springSecurityFilterChain
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ObjectService objectService;

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new RestAccessDeniedHanlder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new UrlAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login/**").anonymous()
                .anyRequest().authenticated().and()
            .csrf().disable()
            .formLogin().loginProcessingUrl("/login").successForwardUrl("/index").and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(objectService);
    }

}
