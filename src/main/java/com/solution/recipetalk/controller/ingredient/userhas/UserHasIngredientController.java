package com.solution.recipetalk.controller.ingredient.userhas;

import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientModifyDTO;
import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientRegisterDTO;
import com.solution.recipetalk.service.ingredient.userhas.EditUserHasIngredientService;
import com.solution.recipetalk.service.ingredient.userhas.FindUserHasIngredientService;
import com.solution.recipetalk.service.ingredient.userhas.RegisterUserHasIngredientService;
import com.solution.recipetalk.service.ingredient.userhas.RemoveUserHasIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class UserHasIngredientController {
    private final RegisterUserHasIngredientService registerUserHasIngredientService;
    private final FindUserHasIngredientService findUserHasIngredientService;
    private final EditUserHasIngredientService editUserHasIngredientService;
    private final RemoveUserHasIngredientService removeUserHasIngredientService;

    @PostMapping("/user/ingredient")
    public ResponseEntity<?> userHasIngredientAdd(@RequestBody List<UserHasIngredientRegisterDTO> dtos) {
        return registerUserHasIngredientService.addUserHasIngredient(dtos);
    }

    @GetMapping("/user/ingredient/{id}")
    public ResponseEntity<?> userHasIngredientDetails(@PathVariable(name = "id") Long userHasIngredientId) {
        return findUserHasIngredientService.findUserHasIngredient(userHasIngredientId);
    }

    @GetMapping("/user/ingredient/page/{startId}")
    public ResponseEntity<?> userHasIngredientList(
            Pageable pageable,
            @PathVariable(name = "startId") Long startId,
            @RequestParam(name = "sort", defaultValue = "expiry_date_immi") String sortElement
    ) {
        return findUserHasIngredientService.findUserHasIngredients(pageable, startId, sortElement);
    }

    @PatchMapping("/user/ingredient/{id}")
    public ResponseEntity<?> userHasIngredientModify(@PathVariable(name = "id") Long userHasIngredientId, @RequestBody UserHasIngredientModifyDTO dto) {
        return editUserHasIngredientService.modifyUserHasIngredient(userHasIngredientId, dto);
    }

    @DeleteMapping("/user/ingredient/{id}")
    public ResponseEntity<?> userHasIngredientRemove(@PathVariable(name = "id") Long userHasIngredientId) {
        return removeUserHasIngredientService.removeUserHasIngredient(userHasIngredientId);
    }
}
