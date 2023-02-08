package com.solution.recipetalk.exception.recipe;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class RecipeNotFoundException extends CustomException {
    public RecipeNotFoundException() {
        super(ErrorCode.RECIPE_NOT_FOUND_EXCEPTION);
    }
}
