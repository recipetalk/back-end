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
    private Long seqNum;
    private String description;
    private String imgUri;


    public static RecipeRowDTO toDTO(RecipeRow recipeRow){
        return RecipeRowDTO.builder()
                .description(recipeRow.getDescription())
                .seqNum(recipeRow.getSeqNum())
                .imgUri(recipeRow.getImageURI())
                .build();
    }
}
