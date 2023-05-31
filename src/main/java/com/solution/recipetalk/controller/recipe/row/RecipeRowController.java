package com.solution.recipetalk.controller.recipe.row;

import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTO;
import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTOWrapper;
import com.solution.recipetalk.dto.recipe.row.RecipeRowRegisterDTO;
import com.solution.recipetalk.dto.recipe.row.RecipeRowRegisterDTOWrapper;
import com.solution.recipetalk.service.recipe.row.FindRecipeRowService;
import com.solution.recipetalk.service.recipe.row.ModifyRecipeRowService;
import com.solution.recipetalk.service.recipe.row.RegisterRecipeRowService;
import com.solution.recipetalk.service.recipe.row.RemoveRecipeRowService;
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
    private final ModifyRecipeRowService modifyRecipeRowService;
    private final RemoveRecipeRowService removeRecipeRowService;

    @PostMapping("/{recipeId}/recipeRow")
    public ResponseEntity<?> recipeRowAdd(@PathVariable(name = "recipeId") Long recipeId, @Valid @NonNull RecipeRowRegisterDTO dto){
        return registerRecipeRowService.registerRecipeRow(recipeId, dto);
    }

    @GetMapping("/{recipeId}/recipeRow")
    public ResponseEntity<?> recipeRowList(@PathVariable(name = "recipeId") Long recipeId){
        return findRecipeRowService.findRecipeRow(recipeId);
    }

    @PatchMapping("/{recipeId}/recipeRow")
    public ResponseEntity<?> recipeRowModify(@PathVariable(name = "recipeId") Long recipeId, @Valid @NonNull RecipeRowModifyDTO dto){
        return modifyRecipeRowService.modifyRecipeRow(recipeId, dto);
    }

    @DeleteMapping("/{recipeId}/recipeRow/{seqNum}")
    public ResponseEntity<?> recipeRowRemove(@PathVariable(name = "recipeId") Long recipeId, @PathVariable(name = "seqNum") Long seqNum){
        return removeRecipeRowService.removeRecipeRow(recipeId, seqNum);
    }
}
