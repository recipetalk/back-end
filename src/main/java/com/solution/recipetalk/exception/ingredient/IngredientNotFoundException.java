package com.solution.recipetalk.exception.ingredient;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class IngredientNotFoundException extends CustomException {
    public IngredientNotFoundException() {
        super(ErrorCode.INGREDIENT_NOT_FOUND_EXCEPTION);
    }
}
