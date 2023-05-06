package com.solution.recipetalk.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthValidator implements ConstraintValidator<CustomLength, Object> {

    private CustomLength length;

    @Override
    public void initialize(CustomLength constraintLength) {
        this.length = constraintLength;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        int valueLength = value.toString().length();
        Boolean validateMinResult = valueLength >= length.min();
        Boolean validateMaxResult = valueLength <= length.max();
        Boolean validateEqResult = true;
        
        // -1 : equals 값을 지정하지 않은 경우
        if (length.equals() != -1){
            validateEqResult = valueLength == length.equals();
        }

        return validateMinResult && validateMaxResult && validateEqResult;
    }
}