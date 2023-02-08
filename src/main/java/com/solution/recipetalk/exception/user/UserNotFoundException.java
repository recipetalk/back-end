package com.solution.recipetalk.exception.user;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND_EXCEPTION);
    }
}
