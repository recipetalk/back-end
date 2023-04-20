package com.solution.recipetalk.service.ingredient.userhas.impl;

import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientResponseDTO;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.service.ingredient.userhas.FindUserHasIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindUserHasIngredientServiceImpl implements FindUserHasIngredientService {
    private final UserHasIngredientRepository userHasIngredientRepository;

    @Override
    public ResponseEntity<?> findUserHasIngredient(Long userHasIngredientId) {
        UserHasIngredient userHasIngredient = userHasIngredientRepository.findById(userHasIngredientId).orElseThrow(IngredientNotFoundException::new);

        return ResponseEntity.ok(UserHasIngredientResponseDTO.toDTO(userHasIngredient));
    }

    @Override
    public ResponseEntity<?> findUserHasIngredients(Pageable pageable, Long startId, String sortElement) {
        Page<UserHasIngredientResponseDTO> allUserIngredient = userHasIngredientRepository.findAllUserIngredient(pageable, startId, sortElement);
        return ResponseEntity.ok(allUserIngredient);
    }
}
