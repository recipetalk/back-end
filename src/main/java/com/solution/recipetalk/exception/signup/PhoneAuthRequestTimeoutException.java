package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class PhoneAuthRequestTimeoutException extends CustomException {
    public PhoneAuthRequestTimeoutException() {
        super(ErrorCode.PHONE_AUTH_REQUEST_TIMEOUT);
    }
}
