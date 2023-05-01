package com.solution.recipetalk.domain.recipe.row.img.repository;

import com.solution.recipetalk.domain.recipe.row.img.RecipeRowImgId;
import com.solution.recipetalk.domain.recipe.row.img.entity.RecipeRowImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRowImgRepository extends JpaRepository<RecipeRowImg, RecipeRowImgId> {

    @Query("SELECT img.URI from RecipeRowImg rri JOIN Image img on rri.image.id = img.id WHERE rri.recipeRow.id = :recipeRowId" )
    List<String>  findImageURIByRecipeRowId(Long recipeRowId);

    @Query("SELECT rri.image.id FROM RecipeRowImg rri WHERE rri.recipeRow.id = :recipeRowId")
    List<Long> findImageIdByRecipeRowId(Long recipeRowId);

    List<RecipeRowImg> findAllByRecipeRowId(Long recipeRowId);
}
