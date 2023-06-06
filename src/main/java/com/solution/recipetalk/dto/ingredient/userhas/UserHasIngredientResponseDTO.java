package com.solution.recipetalk.dto.ingredient.userhas;

import com.solution.recipetalk.domain.ingredient.userhas.entity.IngredientState;
import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHasIngredientResponseDTO {
    private String ingredientName;
    private IngredientState state;
    private String quantity;
    private LocalDate expirationDate;
    private Long ingredientId;
    private Long userHasIngredientId;

    public static UserHasIngredientResponseDTO toDTO(UserHasIngredient entity) {
        return UserHasIngredientResponseDTO.builder()
                .ingredientId(entity.getIngredient() == null? null : entity.getIngredient().getId())
                .ingredientName(entity.getName())
                .state(entity.getState())
                .quantity(entity.getQuantity())
                .expirationDate(entity.getExpirationDate())
                .userHasIngredientId(entity.getId())
                .build();
    }
}
