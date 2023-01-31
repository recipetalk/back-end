package com.solution.recipetalk.exception.comment;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class NotAuthorizedToModifyComment extends CustomException {
    public NotAuthorizedToModifyComment() {
        super(ErrorCode.NOT_AUTHORIZED_TO_MODIFY_COMMENT);
    }
}
