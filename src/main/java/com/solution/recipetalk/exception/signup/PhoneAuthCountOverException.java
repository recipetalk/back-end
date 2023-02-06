package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class PhoneAuthCountOverException extends CustomException {
    public PhoneAuthCountOverException() {
        super(ErrorCode.PHONE_AUTH_COUNT_OVER_EXCEPTION);
    }
}
