package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.annotation.Enum;
import com.solution.recipetalk.domain.recipe.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.yaml.snakeyaml.util.EnumUtils;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeModifyDTO {

    @NotNull
    @Length(max = 1000)
    private String title;

    @NotNull
    @Length(max = 1000)
    private String description;

    @NotNull
    @Length(max = 1000)
    private String thumbnailImgUri;

    @NotNull
    @Enum(enumClass = RecipeLevel.class, ignoreCase = true)
    private String level;

    @NotNull
    @Enum(enumClass = RecipeDurationTime.class, ignoreCase = true)
    private String durationTime;

    @NotNull
    @Enum(enumClass = RecipeQuantityCategory.class, ignoreCase = true)
    private String quantity;

    @NotNull
    @Enum(enumClass = RecipeSituationCategory.class, ignoreCase = true)
    private String situation;

    @NotNull
    @Enum(enumClass = RecipeSortCategory.class, ignoreCase = true)
    private String sort;

    public RecipeLevel getLevel(){
        return ToEnum(RecipeLevel.class, level);
    }

    public RecipeDurationTime getDurationTime(){
        return ToEnum(RecipeDurationTime.class, durationTime);
    }

    public RecipeSituationCategory getSituation(){
        return ToEnum(RecipeSituationCategory.class, situation);
    }

    public RecipeQuantityCategory getQuantity(){
        return ToEnum(RecipeQuantityCategory.class, quantity);
    }

    public RecipeSortCategory getSort(){
        return ToEnum(RecipeSortCategory.class, sort);
    }

    private static <T extends java.lang.Enum<T>> T ToEnum(Class<T> enumType, String value){
        return EnumUtils.findEnumInsensitiveCase(enumType, value);
    }
}
