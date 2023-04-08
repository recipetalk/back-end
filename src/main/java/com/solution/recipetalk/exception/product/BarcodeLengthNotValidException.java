package com.solution.recipetalk.exception.product;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class BarcodeLengthNotValidException extends CustomException {
    public BarcodeLengthNotValidException() {
        super(ErrorCode.BARCODE_LENGTH_NOT_VALID);
    }
}
