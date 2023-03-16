package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class DuplicatedEmailException extends CustomException {
    public DuplicatedEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL_EXISTS);
    }
}
