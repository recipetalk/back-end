package com.solution.recipetalk.exception.comment;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NotAuthorizedToModifyCommentException extends CustomException {
    public NotAuthorizedToModifyCommentException() {
        super(ErrorCode.NOT_AUTHORIZED_TO_MODIFY);
    }
}
