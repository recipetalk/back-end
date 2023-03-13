package com.solution.recipetalk.exception.ingredient.description;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class IngredientDescriptionAlreadyExistsException extends CustomException {
    public IngredientDescriptionAlreadyExistsException() {
        super(ErrorCode.INGREDIENT_DESCRIPTION_ALREADY_EXISTS);
    }
}
