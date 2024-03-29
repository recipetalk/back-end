package com.solution.recipetalk.controller.ingredient.trimming;

import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingByUserReqDTO;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingModifyDTO;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingRegisterDTO;
import com.solution.recipetalk.service.ingredient.trimming.EditIngredientTrimmingService;
import com.solution.recipetalk.service.ingredient.trimming.FindIngredientTrimmingService;
import com.solution.recipetalk.service.ingredient.trimming.RegisterIngredientTrimmingService;
import com.solution.recipetalk.service.ingredient.trimming.RemoveIngredientTrimmingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/ingredient")
public class IngredientTrimmingController {
    private final RegisterIngredientTrimmingService ingredientTrimmingService;
    private final FindIngredientTrimmingService findIngredientTrimmingService;
    private final RemoveIngredientTrimmingService removeIngredientTrimmingService;
    private final EditIngredientTrimmingService editIngredientTrimmingService;

    @PostMapping("/{ingredientId}/trimming")
    public ResponseEntity<?> ingredientTrimmingAdd(@PathVariable(name = "ingredientId") Long ingredientId, @Valid IngredientTrimmingRegisterDTO dto) {
        return ingredientTrimmingService.registerIngredientTrimming(dto, ingredientId);
    }

    @GetMapping("/{ingredientId}/trimming")
    public ResponseEntity<?> ingredientTrimmingList(@PathVariable(name = "ingredientId") Long ingredientId, @PageableDefault(size = 20) Pageable pageable) {
        return findIngredientTrimmingService.findIngredientTrimming(ingredientId, pageable);
    }

    @GetMapping("/trimming/username/{username}")
    public ResponseEntity<?> ingredientTrimmingListByUsername(@PathVariable(name = "username") String username,
                                                              @Valid IngredientTrimmingByUserReqDTO dto){
        return findIngredientTrimmingService.findIngredientTrimmingListByUsername(dto, username);
    }

    @GetMapping("/trimming/{trimmingId}")
    public ResponseEntity<?> ingredientTrimmingDetail(@PathVariable(name = "trimmingId") Long trimmingId) {
        return findIngredientTrimmingService.findIngredientTrimmingDetail(trimmingId);
    }

    @DeleteMapping("/trimming/{trimmingId}")
    public ResponseEntity<?> ingredientTrimmingRemoveByTrimmingId(@PathVariable(name = "trimmingId") Long trimmingId){
        return removeIngredientTrimmingService.removeIngredientTrimmingById(trimmingId);
    }

    @DeleteMapping("/trimming/hard/{trimmingId}")
    public ResponseEntity<?> ingredientTrimmingHardRemoveByTrimmingId(@PathVariable(name = "trimmingId") Long trimmingId){
        return removeIngredientTrimmingService.hardRemoveIngredientTrimmingById(trimmingId);
    }

    @PatchMapping("/trimming/{trimmingId}")
    public ResponseEntity<?> ingredientTrimmingModifyByTrimmingId(@PathVariable(name = "trimmingId") Long trimmingId, @Valid IngredientTrimmingModifyDTO dto){
        return editIngredientTrimmingService.editIngredientTrimming(dto, trimmingId);
    }
}
