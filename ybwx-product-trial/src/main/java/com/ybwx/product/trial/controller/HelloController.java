package com.ybwx.product.trial.controller;

import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.util.vo.ResultVO;
import com.ybwx.product.trial.security.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVO<?> login(){
        SecurityContextHolder.setContext();
        return new ResultVO<>(ResultStatusEnum.OK);
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ResultVO<?> index(){
        String contextHolder = SecurityContextHolder.getContext();
        return new ResultVO<>(contextHolder);
    }

}
