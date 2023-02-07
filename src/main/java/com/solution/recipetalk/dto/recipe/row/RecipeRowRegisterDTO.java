package com.solution.recipetalk.dto.recipe.row;

import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRowRegisterDTO {
    @NonNull
    private Long recipeId;

    @Valid
    private List<RecipeRowInfoDTO> recipeRowInfos;

    public List<RecipeRow> toRecipeRowList(Recipe recipe) {
        return recipeRowInfos.stream().map((recipeRowDescription) -> recipeRowDescription.toRecipeRowEntity(recipe)).toList();
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Builder
    public static class RecipeRowInfoDTO {
        @NonNull
        private String description;

        //TODO : File valid 기능 필요
        private List<MultipartFile> imgs;
        @NonNull
        private Long timer;

        public RecipeRow toRecipeRowEntity(Recipe recipe) {
            return RecipeRow.builder()
                    .recipe(recipe)
                    .description(description)
                    .timer(timer)
                    .build();
        }
    }


}
