package com.ybwx.manager.admin.security.config;

import com.ybwx.manager.admin.security.RestAccessDeniedHanlder;
import com.ybwx.manager.admin.security.UrlAuthenticationEntryPoint;
import com.ybwx.manager.admin.security.filter.DMAuthenticationFilter;
import com.ybwx.manager.admin.security.filter.UsernamePasswordJSONTypeFilter;
import com.ybwx.manager.admin.security.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 这个注解就会自动将目前的类注册成filter springSecurityFilterChain
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ObjectService objectService;

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new RestAccessDeniedHanlder();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
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
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
//            .addFilter(dmAuthenticationFilter());
            .addFilterAt(dmAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public DMAuthenticationFilter dmAuthenticationFilter() throws Exception {
        DMAuthenticationFilter dmAuthenticationFilter = new DMAuthenticationFilter("/login");
        dmAuthenticationFilter.setAuthenticationManager(authenticationManager());
        dmAuthenticationFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        return dmAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(objectService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
