package com.solution.recipetalk.domain.recipe.row.img;


import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRowImgId implements Serializable {
    private RecipeRow recipeRow;
    private Image image;
}
