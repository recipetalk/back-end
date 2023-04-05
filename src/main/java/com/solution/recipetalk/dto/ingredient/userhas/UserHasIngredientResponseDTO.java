package com.solution.recipetalk.dto.ingredient.userhas;

import com.solution.recipetalk.domain.ingredient.userhas.entity.IngredientState;
import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class UserHasIngredientResponseDTO {
    private String ingredientName;
    private IngredientState state;
    private String quantity;
    private LocalDate expirationDate;
    private Long ingredientId;

    public static UserHasIngredientResponseDTO toDTO(UserHasIngredient entity) {
        return UserHasIngredientResponseDTO.builder()
                .ingredientId(entity.getIngredient().getId())
                .ingredientName(entity.getIngredient().getName())
                .state(entity.getState())
                .quantity(entity.getQuantity())
                .expirationDate(entity.getExpirationDate())
                .build();
    }
}
