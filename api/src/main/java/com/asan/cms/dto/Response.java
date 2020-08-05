package com.asan.cms.dto;

import com.asan.cms.type.ErrorCode;

public abstract class Response {
    private final Integer code;
    private final String description;

    public Response(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Response(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getDescription());
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
