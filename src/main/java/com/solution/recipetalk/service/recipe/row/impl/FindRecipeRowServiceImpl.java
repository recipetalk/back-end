package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowDTO;
import com.solution.recipetalk.service.recipe.row.FindRecipeRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FindRecipeRowServiceImpl implements FindRecipeRowService {

    private final RecipeRowRepository recipeRowRepository;
    @Override
    public ResponseEntity<?> findRecipeRow(Long recipeId) {
        //TODO: n+1 터지는지 확인 필요
        List<RecipeRow> recipeRowsByRecipeIdOrderById = recipeRowRepository.findRecipeRowsByRecipeIdOrderById(recipeId);

        List<RecipeRowDTO> sendDto = recipeRowsByRecipeIdOrderById.stream().map(RecipeRowDTO::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(sendDto);
    }
}
