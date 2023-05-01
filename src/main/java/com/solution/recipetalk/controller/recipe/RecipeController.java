package com.solution.recipetalk.controller.recipe;

import com.solution.recipetalk.dto.recipe.RecipeListReqDTO;
import com.solution.recipetalk.dto.recipe.RecipeModifyDTO;
import com.solution.recipetalk.dto.recipe.RecipeRegisterDTO;
import com.solution.recipetalk.service.recipe.FindRecipeService;
import com.solution.recipetalk.service.recipe.ModifyRecipeService;
import com.solution.recipetalk.service.recipe.RegisterRecipeService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/board/recipe")
public class RecipeController {
    private final RegisterRecipeService registerRecipeService;
    private final FindRecipeService findRecipeService;
    private final ModifyRecipeService modifyRecipeService;

    @PostMapping("")
    public ResponseEntity<?> recipeAdd(@Valid @NonNull RecipeRegisterDTO dto) {
        return registerRecipeService.addRecipe(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recipeDetails(@PathVariable(name = "id") Long recipeId){
        return findRecipeService.findRecipeWithId(recipeId);
    }

    @GetMapping("/list")
    public ResponseEntity<?> recipeList(@Valid RecipeListReqDTO dto){
        return findRecipeService.findRecipeList(dto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> recipeByUserDetails(@PathVariable(name = "username") String username){
        return findRecipeService.findRecipeWithUsername(username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> recipeModify(@PathVariable(name = "id") Long recipeId, @Valid @NonNull @RequestBody() RecipeModifyDTO recipeModifyDTO){
        return modifyRecipeService.modifyRecipe(recipeId, recipeModifyDTO);
    }
}
