package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class DuplicatedUserException  extends CustomException {
    public DuplicatedUserException() {
        super(ErrorCode.DUPLICATED_USER_EXIST);
    }
}
