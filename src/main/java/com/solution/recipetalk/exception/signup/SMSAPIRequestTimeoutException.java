package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class SMSAPIRequestTimeoutException extends CustomException {

    public SMSAPIRequestTimeoutException() {
        super(ErrorCode.SMS_API_REQUEST_TIMEOUT);
    }
}
