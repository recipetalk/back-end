package com.solution.recipetalk.exception.fcm;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class FcmNotFoundException extends CustomException {
    public FcmNotFoundException() {
        super(ErrorCode.FCM_TOKEN_NOT_FOUND);
    }
}
