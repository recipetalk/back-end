package com.solution.recipetalk.dto.ingredient.userhas;

import com.solution.recipetalk.domain.ingredient.userhas.entity.IngredientState;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserHasIngredientRegisterDTO {
    @NonNull
    @Size(min = 1, max = 50, message = "입력할 수 있는 한도를 초과하였습니다.")
    private String ingredientName;

    @NonNull
    private Long ingredientId;

    @NonNull
    private IngredientState ingredientState;
    @NonNull
    private LocalDate expirationDate;
    @NonNull
    private String quantity;
}
