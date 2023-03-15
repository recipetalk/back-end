package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class EmailVerificationFailedException extends CustomException {
    public EmailVerificationFailedException() {
        super(ErrorCode.VERIFICATION_FAILED_EXCEPTION);
    }
}
