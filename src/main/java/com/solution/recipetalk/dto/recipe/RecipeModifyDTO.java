package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.domain.recipe.entity.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeModifyDTO {

    @NonNull
    @Length(max = 1000)
    private String title;

    @NonNull
    @Length(max = 1000)
    private String description;

    private MultipartFile thumbnailImg;

    private Boolean isThumbnailDeleted;

    @NonNull
    private RecipeLevel level;

    @NonNull
    private RecipeDurationTime durationTime;

    @NonNull
    private RecipeQuantityCategory quantity;


    private RecipeSituationCategory situation;

    @NonNull
    private RecipeSortCategory sort;

}
