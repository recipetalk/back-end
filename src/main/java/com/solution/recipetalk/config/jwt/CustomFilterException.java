package com.solution.recipetalk.config.jwt;

import org.springframework.security.core.AuthenticationException;

public class CustomFilterException extends AuthenticationException {
    public CustomFilterException(String msg) {
        super(msg);
    }
}
