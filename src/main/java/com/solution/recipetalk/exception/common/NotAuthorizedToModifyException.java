package com.solution.recipetalk.exception.common;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NotAuthorizedToModifyException extends CustomException {
    public NotAuthorizedToModifyException() {
        super(ErrorCode.NOT_AUTHORIZED_TO_MODIFY);
    }
}
