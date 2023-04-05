package com.solution.recipetalk.dto.ingredient.userhas;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class UserHasIngredientRegisterDTO {
    @NonNull
    @Size(min = 1, max = 50, message = "입력할 수 있는 한도를 초과하였습니다.")
    private String ingredientName;

    private Long ingredientId;  // 얘가 Null이면 일반 식재료, not Null이면 Product

    @NonNull
    private String ingredientState;
    @NonNull
    private LocalDate expirationDate;
    @NonNull
    private String quantity;
}
