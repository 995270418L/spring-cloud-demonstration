package com.ybwx.uaa.config;

import com.ybwx.uaa.service.Oauth2UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 认证服务器配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
////                .requestMatchers()
////                    .antMatchers("/oauth/**", "/login/**", "logout/**")
////                    .and()
//                .authorizeRequests()
////                .antMatchers("/oauth/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/logout/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .formLogin().loginProcessingUrl("/singin");
//    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new Oauth2UserDetailService();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
