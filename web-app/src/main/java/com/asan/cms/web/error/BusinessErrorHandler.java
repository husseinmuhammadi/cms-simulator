package com.asan.cms.web.error;

import com.asan.cms.exception.MyDataIntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class BusinessErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessErrorHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MyDataIntegrityViolationException.class})
    public void handleRunTimeException(MyDataIntegrityViolationException e) {
        LOGGER.info("====================================================");
        print(e);
    }


    private void print(Throwable e) {
        LOGGER.error("{} -> {}", e.getClass().getSimpleName(), e.getMessage());
        if (e.getCause() != null)
            print(e.getCause());
    }

}
