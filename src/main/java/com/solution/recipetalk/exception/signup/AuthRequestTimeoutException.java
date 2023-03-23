package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class AuthRequestTimeoutException extends CustomException {
    public AuthRequestTimeoutException() {
        super(ErrorCode.AUTH_REQUEST_EXPIRED_TIMEOUT);
    }
}
