package com.solution.recipetalk.exception.comment;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NoCommentFoundException extends CustomException {
    public NoCommentFoundException() {
        super(ErrorCode.NO_COMMENT_FOUND);
    }
}
