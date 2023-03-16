package com.solution.recipetalk.service.ingredient.trimming.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.row.RemoveIngredientTrimmingRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveIngredientTrimmingRowServiceImpl implements RemoveIngredientTrimmingRowService {
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;
    private final S3Uploader s3Uploader;

    @Override
    public void removeIngredientTrimmingRowByIngredientTrimming(IngredientTrimming ingredientTrimming){
        List<IngredientTrimmingRow> ingredientTrimmingRows = ingredientTrimmingRowRepository.findAllByIngredientTrimming(ingredientTrimming);

        ingredientTrimmingRows.forEach(row -> {
            s3Uploader.deleteFile(row.getImgURI(), S3dir.INGREDIENT_TRIMMING_ROW_IMG_DIR);
            ingredientTrimmingRowRepository.delete(row);
        });
    }
}
