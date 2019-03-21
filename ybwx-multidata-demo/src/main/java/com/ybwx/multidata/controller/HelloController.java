package com.ybwx.multidata.controller;

import com.ybwx.multidata.entity.UserEntity;
import com.ybwx.multidata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void getUser(){
        // 从 从库里面找
        log.info("get user id: 36 ");
        userService.selectByPrimaryKey(36L);
    }

    @RequestMapping(value = "/insert/user", method = RequestMethod.GET)
    public void insertUser(){
        log.info("inset into user ");
        UserEntity  userEntity = new UserEntity();
        userEntity.setCreatedTime(new Date());
        userEntity.setEmail("xxxxxx@email.com");
        userEntity.setEmailValid(1);
        userEntity.setMobile("13231231231");
        userEntity.setMobileValid(1);
        userEntity.setPassword("xxxxxxxxxxxxxxxxxsssssssssss");
        userEntity.setRoot(1);
        userEntity.setSalt("123124123");
        userEntity.setUpdatedTime(new Date());
        userEntity.setUsername("steve-test-master");
        userEntity.setValid(1);
        userEntity.setToken("sfgdsagwerrfsdfsgsdagfasdrfe");
    }

}
