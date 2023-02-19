package com.solution.recipetalk.controller.recipe.row;

import com.solution.recipetalk.dto.recipe.row.RecipeRowRegisterDTO;
import com.solution.recipetalk.service.recipe.row.FindRecipeRowService;
import com.solution.recipetalk.service.recipe.row.RegisterRecipeRowService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/board/recipe")
public class RecipeRowController {

    private final RegisterRecipeRowService registerRecipeRowService;
    private final FindRecipeRowService findRecipeRowService;

    @PostMapping("/{recipeId}/recipeRow")
    public ResponseEntity<?> recipeRowAdd(@PathVariable(name = "recipeId") Long recipeId, @Valid @NonNull List<RecipeRowRegisterDTO> dtoList){
        return registerRecipeRowService.registerRecipeRow(recipeId, dtoList);
    }

    @GetMapping("/{recipeId}/recipeRow")
    public ResponseEntity<?> recipeRowList(@PathVariable(name = "recipeId") Long recipeId){
        return findRecipeRowService.findRecipeRow(recipeId);
    }
}
