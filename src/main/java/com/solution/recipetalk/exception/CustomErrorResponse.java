package com.solution.recipetalk.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class CustomErrorResponse {
    private String errorMessage;
    private HttpStatus httpStatus;
    private String code;

    public CustomErrorResponse(HttpStatus httpStatus, String errorMessage){
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public CustomErrorResponse(ErrorCode code){
        errorMessage = code.getMessage();
        httpStatus = code.getHttpStatus();
        this.code = code.getCode();
    }
}
