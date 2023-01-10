package com.solution.recipetalk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleApiRequestException(Exception exception){
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException exception){
        ErrorCode errorCode = exception.getErrorCode();
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorCode);
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }
}
