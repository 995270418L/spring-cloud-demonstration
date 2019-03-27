package com.ybwx.uaa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Oauth2UserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("获取用户信息 start ..... ");
        // 这里调用 ldap 获取用户信息
        Set<SimpleGrantedAuthority> roleCollection = new HashSet<>();
        roleCollection.add(new SimpleGrantedAuthority("ADMIN"));
        return new User("steve", "$2a$10$bFJB6f.onGpvkhiFaNsvOOELNRZwFdfSURiTWQuaR5znwTrILy8Dy", roleCollection);
    }

}
