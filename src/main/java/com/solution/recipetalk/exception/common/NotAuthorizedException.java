package com.solution.recipetalk.exception.common;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NotAuthorizedException extends CustomException {
    public NotAuthorizedException() {
        super(ErrorCode.NOT_AUTHORIZED);
    }
}
