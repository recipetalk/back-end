package com.solution.recipetalk.service.ingredient.trimming.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowRegisterDTO;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.row.RegisterIngredientTrimmingRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterIngredientTrimmingRowServiceImpl implements RegisterIngredientTrimmingRowService {

    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final S3Uploader s3Uploader;
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;


    @Override
    public ResponseEntity<?> registerIngredientTrimmingRow(List<IngredientTrimmingRowRegisterDTO> dtoList, Long ingredientTrimmingId) {
        IngredientTrimming findIngredientTrimming = ingredientTrimmingRepository.findById(ingredientTrimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);

        List<IngredientTrimmingRow> createdIngredientTrimmingRows = dtoList.stream()
                .map(dto -> {
                    try {
                        return dto.toIngredientTrimmingRow(findIngredientTrimming,
                                s3Uploader.upload(dto.getImg(), S3dir.INGREDIENT_TRIMMING_ROW_IMG_DIR));
                    } catch (IOException e) {
                        throw new ImageUploadFailedException();
                    }
                }).toList();

        ingredientTrimmingRowRepository.saveAll(createdIngredientTrimmingRows);
        return ResponseEntity.ok(null);
    }
}
