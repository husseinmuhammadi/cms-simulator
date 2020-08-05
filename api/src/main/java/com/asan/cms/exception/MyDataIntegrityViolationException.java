package com.asan.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MyDataIntegrityViolationException extends RuntimeException {
    public MyDataIntegrityViolationException() {
        super();
    }

    public MyDataIntegrityViolationException(String message) {
        super(message);
    }

    public MyDataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDataIntegrityViolationException(Throwable cause) {
        super(cause);
    }

    protected MyDataIntegrityViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
