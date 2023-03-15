package com.solution.recipetalk.exception.user;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class UserBlockExistException extends CustomException {
    public UserBlockExistException() {
        super(ErrorCode.USER_BLOCK_EXIST_EXCEPTION);
    }
}
