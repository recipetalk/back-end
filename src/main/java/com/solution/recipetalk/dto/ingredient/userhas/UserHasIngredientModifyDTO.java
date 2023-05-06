package com.solution.recipetalk.dto.ingredient.userhas;

import com.solution.recipetalk.annotation.CustomLength;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class UserHasIngredientModifyDTO {
    @CustomLength(max = 10, message = "state")
    private String state;
    @CustomLength(max = 10, message = "quantity")
    private String quantity;
    private LocalDate expirationDate;
}
