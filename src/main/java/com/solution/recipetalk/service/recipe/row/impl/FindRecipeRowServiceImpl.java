package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.img.repository.RecipeRowImgRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowDTO;
import com.solution.recipetalk.service.recipe.row.FindRecipeRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FindRecipeRowServiceImpl implements FindRecipeRowService {

    private final RecipeRowRepository recipeRowRepository;
    private final RecipeRowImgRepository recipeRowImgRepository;
    @Override
    public ResponseEntity<?> findRecipeRow(Long recipeId) {
        List<RecipeRow> recipeRowsByRecipeIdOrderById = recipeRowRepository.findRecipeRowsByRecipeIdOrderById(recipeId);

        List<RecipeRowDTO> sendDto = new ArrayList<>();

        for (int i = 0; i < recipeRowsByRecipeIdOrderById.size(); i++) {
            RecipeRow target = recipeRowsByRecipeIdOrderById.get(i);
            Long recipeRowId = target.getId();

            List<String> imgUris = recipeRowImgRepository.findImageURIByRecipeRowId(recipeRowId);

            sendDto.add(RecipeRowDTO.toDTO(target, imgUris, (long)i, recipeRowId));
        }

        return ResponseEntity.ok(sendDto);
    }
}
