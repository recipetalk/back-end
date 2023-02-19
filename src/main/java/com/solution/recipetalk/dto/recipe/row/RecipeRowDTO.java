package com.solution.recipetalk.dto.recipe.row;

import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRowDTO {
    private Long sequenceNum;
    private String description;
    private List<String> imgUris;


    public static RecipeRowDTO toDTO(RecipeRow recipeRow, List<String> imgUris, Long sequenceNum){
        return RecipeRowDTO.builder()
                .description(recipeRow.getDescription())
                .sequenceNum(sequenceNum)
                .imgUris(imgUris)
                .build();
    }
}
