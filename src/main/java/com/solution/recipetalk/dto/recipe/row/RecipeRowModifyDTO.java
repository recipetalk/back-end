package com.solution.recipetalk.dto.recipe.row;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRowModifyDTO {
    @NonNull
    private String description;

    private MultipartFile img;
    @NonNull
    private Long seqNum;


    public RecipeRow toRecipeRowEntity(Recipe recipe, String imgURI) {
        return RecipeRow.builder()
                .recipe(recipe)
                .description(description)
                .seqNum(seqNum)
                .imageURI(imgURI)
                .build();
    }

    public RecipeRowRegisterDTO toRegisterDTO(){
        return RecipeRowRegisterDTO.builder()
                .description(description)
                .img(img)
                .seqNum(seqNum)
                .build();
    }
}

