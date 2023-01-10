package com.solution.recipetalk.exception.common;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(){
        super(ErrorCode.NOT_FOUND);
    }
}
