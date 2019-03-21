package com.ybwx;

import com.ybwx.manager.admin.AdminApplication;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {AdminApplication.class})
@RunWith(SpringRunner.class)
public class PasswordGenerator {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test1(){
        String result = bCryptPasswordEncoder.encode("admin");
        System.out.println(result);
    }
}
