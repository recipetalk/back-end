package com.solution.recipetalk.exception.board;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class CannotFindBoardException extends CustomException {
    public CannotFindBoardException() {
        super(ErrorCode.CANNOT_FIND_BOARD);
    }
}
