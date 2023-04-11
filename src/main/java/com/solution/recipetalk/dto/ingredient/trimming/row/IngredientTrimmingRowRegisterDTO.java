package com.solution.recipetalk.dto.ingredient.trimming.row;

import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTrimmingRowRegisterDTO {
    @NonNull
    private String description;

    private MultipartFile img;
    @NonNull
    private Long trimmingSeq;

    public IngredientTrimmingRow toIngredientTrimmingRow(IngredientTrimming ingredientTrimming, String imgUri){
        return IngredientTrimmingRow.builder()
                .ingredientTrimming(ingredientTrimming)
                .imgURI(imgUri)
                .description(this.description)
                .trimmingSeq(this.trimmingSeq)
                .build();
    }
}
