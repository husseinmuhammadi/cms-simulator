package com.tasnim.trade.eshop.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:security.properties")
public class PasswordComplexityConfiguration {

    @Value("${password.complexity.length.min}")
    private int minLength;

    @Value("${password.complexity.length.max}")
    private int maxLength;

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
