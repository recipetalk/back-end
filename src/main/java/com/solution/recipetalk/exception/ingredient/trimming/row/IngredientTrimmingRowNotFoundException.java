package com.solution.recipetalk.exception.ingredient.trimming.row;

import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;

public class IngredientTrimmingRowNotFoundException extends CustomException {
    public IngredientTrimmingRowNotFoundException() {
        super(ErrorCode.INGREDIENT_TRIMMING_ROW_NOT_FOUND_EXCEPTION);
    }
}
