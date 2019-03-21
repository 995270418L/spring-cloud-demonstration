package com.ybwx.common.controller;

import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.util.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 404 500 ... error handler
 */
@Slf4j
@RestController
public class MainErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    @ResponseBody
    public ResultVO<?> handlerError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode){
            case 404: return  new ResultVO<>(ResultStatusEnum.NOT_FOUND);
            case 500: return new ResultVO<>(ResultStatusEnum.ERROR);
        }
        return null;
    }
    @RequestMapping(value = "/oauth/errror")
    public ResultVO<?> handlerOauth2Error(HttpServletRequest request){
        log.info(" /oauth/error endpoint reflect ");
        return new ResultVO<>(ResultStatusEnum.UNAUTHORIZED);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
