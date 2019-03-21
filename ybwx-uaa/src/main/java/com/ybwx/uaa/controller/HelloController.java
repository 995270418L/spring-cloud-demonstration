package com.ybwx.uaa.controller;

import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.util.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @RequestMapping(value = "/api")
    public ResultVO<?> index(){
        log.info("hello index");
        return new ResultVO<>(ResultStatusEnum.OK);
    }

}
