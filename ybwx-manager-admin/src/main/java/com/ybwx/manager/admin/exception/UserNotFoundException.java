package com.ybwx.manager.admin.exception;

import com.ybwx.common.exception.CompanyServiceException;

public class UserNotFoundException extends CompanyServiceException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
