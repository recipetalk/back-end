package com.solution.recipetalk.service.ingredient.userhas.impl;

import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientRegisterDTO;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.ingredient.userhas.RegisterUserHasIngredientService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterUserHasIngredientServiceImpl implements RegisterUserHasIngredientService {
    private final UserHasIngredientRepository userHasIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> addUserHasIngredient(List<UserHasIngredientRegisterDTO> dtos) {
        UserDetail currentUser = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);

        for(UserHasIngredientRegisterDTO i : dtos) {
            userHasIngredientRepository.save(UserHasIngredient.builder()
                            .user(currentUser)
                            .name(i.getIngredientName())
                            .ingredient(ingredientRepository.findById(i.getIngredientId()).orElseThrow(IngredientNotFoundException::new))
                            .state(i.getIngredientState())
                            .quantity(i.getQuantity())
                            .expirationDate(i.getExpirationDate())
                    .build());
        }

        return ResponseEntity.ok(null);
    }

}
