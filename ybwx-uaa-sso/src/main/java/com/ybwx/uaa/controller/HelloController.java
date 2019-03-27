package com.ybwx.uaa.controller;

import com.ybwx.common.util.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class HelloController {

    @RestController
    public class UserController {
        @GetMapping("/user/me")
        public ResultVO<?> user(Principal principal) {
            return new ResultVO<>(principal);
        }
    }

}
