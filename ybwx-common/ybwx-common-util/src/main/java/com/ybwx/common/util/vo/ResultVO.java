package com.ybwx.common.util.vo;

import com.ybwx.common.enums.ResultStatusEnum;
import lombok.Data;

/**
 * @描述 :
 * @作者 :	pst
 * @日期 :	2017/10/10
 * @时间 :	17:21
 */
@Data
public class ResultVO<T> {

    private Integer code;
    private String message;
    private T data;

    public ResultVO() {
        this(ResultStatusEnum.OK);
    }

    public ResultVO(T data) {
        this();
        this.data = data;
    }

    public ResultVO(ResultStatusEnum resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
    }

    public ResultVO(String message, ResultStatusEnum resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.message = message;
    }

    public ResultVO(ResultStatusEnum resultStatusEnum, T data) {
        this(resultStatusEnum);
        this.data = data;
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
