package com.solution.recipetalk.service.ingredient.trimming.impl;

import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingResult;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingDetailResult;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingFindResDTO;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.service.ingredient.trimming.FindIngredientTrimmingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindIngredientTrimmingServiceImpl implements FindIngredientTrimmingService {

    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;

    @Override
    public ResponseEntity<?> findIngredientTrimming(Long ingredientId){
        List<IngredientTrimmingResult> ingredientTrimmingResults = ingredientTrimmingRepository.findIngredientTrimmingResultById(ingredientId).orElseThrow(IngredientTrimmingNotFoundException::new);

        return ResponseEntity.ok(ingredientTrimmingResults);
    }

    @Override
    public ResponseEntity<?> findIngredientTrimmingDetail(Long ingredientId, Long trimmingId){
        IngredientTrimmingDetailResult ingredientTrimmingDetail = ingredientTrimmingRepository.findIngredientTrimmingDetailResultById(ingredientId, trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);

        List<IngredientTrimmingRow> trimmingRows = ingredientTrimmingRowRepository
                .findAllByIngredientTrimming(ingredientTrimmingRepository
                        .findById(trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new));

        return ResponseEntity.ok(IngredientTrimmingFindResDTO.fromIngredientTrimmingDetailResultAndTrimmingRows(ingredientTrimmingDetail, trimmingRows));
    }



}
