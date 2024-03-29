package com.solution.recipetalk.dto.ingredient.trimming.row;

import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTrimmingRowResDTO {
    private String description;

    private String imgUri;
    private Long trimmingSeq;
    private Long id;

    public static IngredientTrimmingRowResDTO fromTrimmingRow(IngredientTrimmingRow trimmingRow){
        IngredientTrimmingRowResDTO dto = new IngredientTrimmingRowResDTO();
        dto.setDescription(trimmingRow.getDescription());
        dto.setTrimmingSeq(trimmingRow.getTrimmingSeq());
        dto.setImgUri(trimmingRow.getImgURI());
        dto.setId(trimmingRow.getId());
        return dto;
    }
}
