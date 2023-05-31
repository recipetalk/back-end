package com.solution.recipetalk.exception.user;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class UserFollowExistException extends CustomException {
    public UserFollowExistException() {
        super(ErrorCode.USER_FOLLOW_EXIST);
    }
}
