package com.solution.recipetalk.exception.s3;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class ImageUploadFailedException extends CustomException {
    public ImageUploadFailedException() {
        super(ErrorCode.IMAGE_UPLOAD_FAILED_EXCEPTION);
    }
}
