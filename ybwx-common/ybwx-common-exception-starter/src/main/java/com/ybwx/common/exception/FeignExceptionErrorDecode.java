package com.ybwx.common.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.util.StringUtils;
import com.ybwx.common.util.json.JSONSnakeUtils;
import com.ybwx.common.util.vo.ResultVO;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FeignExceptionErrorDecode implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        Exception exception = null;
        try {
            ResultVO<?> result = JSONSnakeUtils.readValue(StringUtils.InputStreamToString(response.body().asInputStream()), ResultVO.class);
            log.error("被调用服务异常信息: {}", result);
            exception = new HystrixBadRequestException(result.getMessage());
        }catch(IllegalStateException e){
            exception = new CompanyServiceException(e.getMessage());
        }catch (IOException e) {
            e.printStackTrace();
        }
        if(exception == null){
            exception = new CompanyServiceException(ResultStatusEnum.ERROR.getMessage());
        }
        return exception;
    }

}
