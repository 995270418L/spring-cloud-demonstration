package com.ybwx.manager.admin.controller;

import com.ybwx.common.util.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class HomeController {

    public static final String ERROR = "/error";

    @RequestMapping("/index")
    public ResultVO<?> index(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication infomation: {} ", authentication);
        return new ResultVO<>(authentication);
    }

}
