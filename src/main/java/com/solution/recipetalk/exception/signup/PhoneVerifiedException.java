package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class PhoneVerifiedException extends CustomException {
    public PhoneVerifiedException() {
        super(ErrorCode.PHONE_VERIFIED_EXCEPTION);
    }
}
