package com.solution.recipetalk.service.ingredient.userhas.impl;

import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientModifyDTO;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.service.ingredient.userhas.EditUserHasIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EditUserHasIngredientServiceImpl implements EditUserHasIngredientService {
    private final UserHasIngredientRepository userHasIngredientRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public ResponseEntity<?> modifyUserHasIngredient(Long userHasIngredientId, UserHasIngredientModifyDTO dto) {
        UserHasIngredient byId = userHasIngredientRepository.findById(userHasIngredientId).orElseThrow(IngredientNotFoundException::new);
        Optional<Ingredient> byName = ingredientRepository.findByName(dto.getIngredientName());

        byName.ifPresentOrElse(
                ingredient -> byId.updateUserHasIngredient(dto, ingredient),
                () -> byId.updateUserHasIngredient(dto, null)
        );

        userHasIngredientRepository.save(byId);

        return ResponseEntity.ok(null);
    }
}