package com.solution.recipetalk.exception.signup;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class DuplicatedNicknameException extends CustomException {
    public DuplicatedNicknameException() {
        super(ErrorCode.DUPLICATED_NICKNAME_EXIST);
    }
}
