package com.solution.recipetalk.exception.comment;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class CommentIsDeletedException extends CustomException {
    public CommentIsDeletedException() {
        super(ErrorCode.COMMENT_IS_DELETED);
    }
}
