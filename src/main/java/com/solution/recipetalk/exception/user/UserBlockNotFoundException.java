package com.solution.recipetalk.exception.user;

import com.solution.recipetalk.domain.user.block.entity.UserBlock;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class UserBlockNotFoundException extends CustomException {
    public UserBlockNotFoundException() {
        super(ErrorCode.USER_BLOCK_NOT_FOUND);
    }
}
