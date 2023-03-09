package com.solution.recipetalk.dto.ingredient.trimming;

import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowModifyDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTrimmingModifyListDTO {
    private List<IngredientTrimmingRowModifyDTO> dtos;
}