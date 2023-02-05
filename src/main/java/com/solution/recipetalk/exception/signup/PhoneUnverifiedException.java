package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class PhoneUnverifiedException extends CustomException {
    public PhoneUnverifiedException() {
        super(ErrorCode.PHONE_UNVERIFIED_EXCEPTION);
    }
}
