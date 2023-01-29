package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class SMSAuthRequestTimeoutException extends CustomException {

    public SMSAuthRequestTimeoutException() {
        super(ErrorCode.SMS_AUTH_REQUEST_TIMEOUT);
    }
}
