package com.solution.recipetalk.exception.comment;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NotAdminUserException extends CustomException {
    public NotAdminUserException() {
        super(ErrorCode.NOT_ADMIN);
    }
}
