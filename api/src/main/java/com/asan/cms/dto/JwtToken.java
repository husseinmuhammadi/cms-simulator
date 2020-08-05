package com.asan.cms.dto;

public class JwtToken {
    private final String token;
    private final String data;

    public JwtToken(String token, String data) {
        this.token = token;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public String getData() {
        return data;
    }
}
