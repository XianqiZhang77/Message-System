package com.ray.message.exception;

import com.ray.message.enums.ErrorCode;

public class ServiceException extends Exception {

    ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
