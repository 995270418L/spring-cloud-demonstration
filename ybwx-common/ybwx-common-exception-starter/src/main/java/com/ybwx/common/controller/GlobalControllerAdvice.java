package com.ybwx.common.controller;

import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.exception.CompanyServiceException;
import com.ybwx.common.util.vo.ResultVO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(CompanyServiceException.class)
    public ResultVO<?> exception(CompanyServiceException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(e.getMessage(), ResultStatusEnum.ERROR);
    }

    /**
     * 服务调用方没找到对应的服务
     * @param e
     * @return
     */
    @ExceptionHandler(ClientException.class)
    public ResultVO<?> exception(ClientException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(ResultStatusEnum.ERROR);
    }

    /**
     * 被调用方服务异常
     * @param e
     * @return
     */
    @ExceptionHandler(FeignException.class)
    public ResultVO<?> exception(FeignException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(ResultStatusEnum.ERROR);
    }

    @ExceptionHandler(HystrixBadRequestException.class)
    public ResultVO<?> exception(HystrixBadRequestException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(110, e.getMessage());
    }
}
