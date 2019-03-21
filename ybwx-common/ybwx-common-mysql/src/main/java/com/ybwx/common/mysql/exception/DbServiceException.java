package com.ybwx.common.mysql.exception;


import com.ybwx.common.exception.CompanyServiceException;

public class DbServiceException extends CompanyServiceException {

    public DbServiceException(String message) {
        super(message);
    }

}
