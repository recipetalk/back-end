package com.solution.recipetalk.annotation;

import com.solution.recipetalk.exception.comment.NotAuthorizedToModifyCommentException;
import com.solution.recipetalk.exception.common.NotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<Enum, Object> {

    private Enum annotation;

    @Override
    public void initialize(Enum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }
        return Arrays.stream(annotation.enumClass().getEnumConstants())
                .anyMatch(anEnum -> anEnum.toString().equals(value.toString()));
    }
}