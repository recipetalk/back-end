package com.solution.recipetalk.exception.ingredient.trimming;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class IngredientTrimmingNotFoundException extends CustomException {
    public IngredientTrimmingNotFoundException() {
        super(ErrorCode.INGREDIENT_TRIMMING_NOT_FOUND_EXCEPTION);
    }
}
