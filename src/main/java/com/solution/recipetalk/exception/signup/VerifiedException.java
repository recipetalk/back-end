package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class VerifiedException extends CustomException {
    public VerifiedException() {
        super(ErrorCode.VERIFIED_EXCEPTION);
    }
}
