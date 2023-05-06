package com.solution.recipetalk.service.ingredient.userhas.impl;

import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.service.ingredient.userhas.RemoveUserHasIngredientService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveUserHasIngredientServiceImpl implements RemoveUserHasIngredientService {
    private final UserHasIngredientRepository userHasIngredientRepository;
    @Override
    public ResponseEntity<?> removeUserHasIngredient(Long userHasIngredientId) {
        Long loginUserId = ContextHolder.getUserLoginId();
        UserHasIngredient byId = userHasIngredientRepository.findById(userHasIngredientId).orElseThrow(IngredientNotFoundException::new);
        if (!Objects.equals(loginUserId, byId.getUser().getId())){
            throw new NotAuthorizedException();
        }
        userHasIngredientRepository.delete(byId);
        return ResponseEntity.ok(null);
    }
}
