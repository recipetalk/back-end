package com.solution.recipetalk.exception.ingredient.description;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class IngredientDescriptionNotFoundException extends CustomException {
    public IngredientDescriptionNotFoundException() {
        super(ErrorCode.INGREDIENT_DESCRIPTION_NOT_FOUND_EXCEPTION);
    }
}
