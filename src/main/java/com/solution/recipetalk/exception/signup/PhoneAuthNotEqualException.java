package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class PhoneAuthNotEqualException extends CustomException {
    public PhoneAuthNotEqualException() {
        super(ErrorCode.PHONE_AUTH_NOT_EQUAL_EXCEPTION);
    }
}
