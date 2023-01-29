package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;


public class PhoneAuthEntityNotFoundException extends CustomException {

    public PhoneAuthEntityNotFoundException() {
        super(ErrorCode.PHONE_AUTH_ENTITY_NOT_FOUND);
    }
}
