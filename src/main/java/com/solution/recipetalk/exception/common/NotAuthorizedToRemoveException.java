package com.solution.recipetalk.exception.common;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NotAuthorizedToRemoveException extends CustomException {
    public NotAuthorizedToRemoveException() {
        super(ErrorCode.NOT_AUTHORIZED_TO_REMOVE);
    }
}
