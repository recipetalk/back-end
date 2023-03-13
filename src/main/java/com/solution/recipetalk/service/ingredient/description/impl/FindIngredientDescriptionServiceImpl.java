package com.solution.recipetalk.service.ingredient.description.impl;

import com.solution.recipetalk.domain.ingredient.description.repository.IngredientDescriptionRepository;
import com.solution.recipetalk.dto.ingredient.description.IngredientDescriptionResponseDTO;
import com.solution.recipetalk.exception.ingredient.description.IngredientDescriptionNotFoundException;
import com.solution.recipetalk.service.ingredient.description.FindIngredientDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindIngredientDescriptionServiceImpl implements FindIngredientDescriptionService {
    private final IngredientDescriptionRepository ingredientDescriptionRepository;

    @Override
    public ResponseEntity<?> findIngredientDescription(Long ingredientId) {
        IngredientDescriptionResponseDTO dto = ingredientDescriptionRepository.findByIngredientId(ingredientId).orElseThrow(IngredientDescriptionNotFoundException::new);
        return ResponseEntity.ok(dto);
    }
}
