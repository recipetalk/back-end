package com.solution.recipetalk.service.ingredient.impl;

import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.dto.ingredient.IngredientFindResultDTO;
import com.solution.recipetalk.service.ingredient.FindIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FindIngredientServiceImpl implements FindIngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    public ResponseEntity<?> findIngredientListByNameComponent(String nameComponent) {
        List<IngredientFindResultDTO> ingredientResultDTOs = ingredientRepository.findAllStartWith(nameComponent);
        return ResponseEntity.ok(ingredientResultDTOs);
    }

    @Override
    public ResponseEntity<?> findIngredientListByNameComponentPage(String nameComponent) {
        List<IngredientFindResultDTO> dtos = ingredientRepository.findSomeStartWith(nameComponent);
        return ResponseEntity.ok(dtos);
    }
}
