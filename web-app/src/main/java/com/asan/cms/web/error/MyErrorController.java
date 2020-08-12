package com.asan.cms.web.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyErrorController.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Exception e) {
        LOGGER.error(e.getMessage(), e);
        LOGGER.info("Do some logic before show the general error page");

        ModelAndView errorPage = new ModelAndView("errorPage");
        String errorMsg = "";

        HttpStatus httpStatus = HttpStatus.resolve(getErrorCode(request));
        switch (httpStatus) {
            case NOT_FOUND:
                LOGGER.info("Show the page error-400 because of requested url not found!");
                return "error/404";
            case INTERNAL_SERVER_ERROR:
                LOGGER.info("Show the page error-500 because of internal error!");
                return "error/500";
        }
        return "error";
    }

    private int getErrorCode(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        LOGGER.info("Status is instance of {}", statusCode.getClass().getSimpleName());
        return (Integer) statusCode;
    }
}
