package com.solution.recipetalk.dto.ingredient.trimming;

import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingDetailResult;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowResDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTrimmingFindResDTO {
    String description;
    List<IngredientTrimmingRowResDTO> trimmingRows;

    public static IngredientTrimmingFindResDTO fromIngredientTrimmingDetailResultAndTrimmingRows(IngredientTrimmingDetailResult result, List<IngredientTrimmingRow> trimmingRows){
        IngredientTrimmingFindResDTO dto = new IngredientTrimmingFindResDTO();
        dto.setDescription(result.getDescription());
        dto.setTrimmingRows(trimmingRows.stream().map(IngredientTrimmingRowResDTO::fromTrimmingRow).toList());
        return dto;
    }

}
