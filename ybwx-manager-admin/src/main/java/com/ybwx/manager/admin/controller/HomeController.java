package com.ybwx.manager.admin.controller;

import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.util.vo.ResultVO;
import com.ybwx.manager.admin.vo.LoginRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class HomeController {

    public static final String ERROR = "/error";

    @RequestMapping("/index")
    public ResultVO<?> index(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication infomation: {} ", authentication);
        return new ResultVO<>(ResultStatusEnum.OK);
    }

}
