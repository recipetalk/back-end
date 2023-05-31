package com.solution.recipetalk.exception.user;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class UserInformationMatchException extends CustomException {
    public UserInformationMatchException() {
        super(ErrorCode.USER_INFORMATION_NOT_MATCH);
    }
}
