package com.solution.recipetalk.controller.ingredient;

import com.solution.recipetalk.service.ingredient.FindIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class IngredientController {
    private final FindIngredientService findIngredientService;

    @GetMapping("/ingredient/{nameComponent}")
    public ResponseEntity<?> recipeIngredientListForRegister(
            @PathVariable(name = "nameComponent") String nameComponent, Pageable pageable
    ) {
        return findIngredientService.findIngredientListByNameComponentPage(nameComponent, pageable);
    }
}
