package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class UnverifiedException extends CustomException {
    public UnverifiedException() {
        super(ErrorCode.UNVERIFIED_EXCEPTION);
    }
}
