package com.ybwx.common.exception;

import java.io.Serializable;

/**
 * micro service invoke logic exception
 */
public class LogicException extends BaseException implements Serializable {

    public LogicException(String message){
        super(message);
    }

}
