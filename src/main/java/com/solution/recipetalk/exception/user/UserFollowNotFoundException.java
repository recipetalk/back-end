package com.solution.recipetalk.exception.user;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class UserFollowNotFoundException extends CustomException {
    public UserFollowNotFoundException() {
        super(ErrorCode.USER_FOLLOW_NOT_FOUND);
    }
}
