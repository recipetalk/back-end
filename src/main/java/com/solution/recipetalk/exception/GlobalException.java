package com.solution.recipetalk.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleApiRequestException(Exception exception){
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException exception){
        ErrorCode errorCode = exception.getErrorCode();
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorCode);
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> handleApiRequestException2(NullPointerException exception){
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception){
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, "정확하게 입력하였는지 확인바랍니다.");
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }


    // @Valid exception 핸들링
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String exceptionMessage = exception.getMessage().substring(exception.getMessage().lastIndexOf("default message")+17, exception.getMessage().length()-3);
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, "값이 유효하지 않습니다. (" + exceptionMessage + ")");
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    // JSON 파싱 에러 + NonNull 에러핸들링
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> jsonParseException(HttpMessageNotReadableException exception){
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, "정확한 값을 입력해주세요.");
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    // path variable 내 검증 에러핸들링
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> pathVariableException(ConstraintViolationException exception){
        String exceptionMessage = "";
        try{
            exceptionMessage = exception.getMessage().split(": ")[1];
        }catch (Exception e) {
        }

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST, "값이 유효하지 않습니다. (" + exceptionMessage + ")");
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
