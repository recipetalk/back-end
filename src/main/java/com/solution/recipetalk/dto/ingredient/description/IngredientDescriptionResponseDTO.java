package com.solution.recipetalk.dto.ingredient.description;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDescriptionResponseDTO {
    private String title;
    private String ingredientName;
    private String imgURI;
    private String description;
    private Boolean isModified;
}
