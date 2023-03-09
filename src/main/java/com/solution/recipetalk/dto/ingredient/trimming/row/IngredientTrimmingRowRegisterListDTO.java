package com.solution.recipetalk.dto.ingredient.trimming.row;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTrimmingRowRegisterListDTO {
    private List<IngredientTrimmingRowRegisterDTO> dtos;
}
