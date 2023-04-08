package com.solution.recipetalk.service.ingredient.trimming.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowModifyDTO;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.exception.ingredient.trimming.row.IngredientTrimmingRowNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.row.EditIngredientTrimmingRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EditIngredientTrimmingRowServiceImpl implements EditIngredientTrimmingRowService {
    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;
    private final S3Uploader s3Uploader;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> editIngredientTrimmingRow(List<IngredientTrimmingRowModifyDTO> dtoList, Long trimmingId) {
        Long userLoginId = ContextHolder.getUserLoginId();
        UserDetail currentUser = userDetailRepository.findById(userLoginId).orElseThrow(UserNotFoundException::new);
        IngredientTrimming ingredientTrimming = ingredientTrimmingRepository.findById(trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);

        if (!ingredientTrimming.getBoard().getWriter().equals(currentUser)){
            throw new CustomException(ErrorCode.NOT_AUTHORIZED);
        }
        List<IngredientTrimmingRow> ingredientTrimmingRows = dtoList.stream().map(dto -> {
            IngredientTrimmingRow ingredientTrimmingRow = ingredientTrimmingRowRepository
                    .findByIngredientTrimmingAndTrimmingSeq(ingredientTrimming, dto.getTrimmingSeq())
                    .orElseThrow(IngredientTrimmingRowNotFoundException::new);

            s3Uploader.deleteFile(ingredientTrimmingRow.getImgURI(), S3dir.INGREDIENT_TRIMMING_ROW_IMG_DIR);

            try {
                String imgURI = s3Uploader.upload(dto.getImg(), S3dir.INGREDIENT_TRIMMING_ROW_IMG_DIR);
                ingredientTrimmingRow.changeImageURI(imgURI);
                ingredientTrimmingRow.changeDescription(dto.getDescription());
                ingredientTrimmingRow.changeTrimmingSeq(dto.getTrimmingSeq());
                return ingredientTrimmingRow;
            } catch (IOException e) {
                throw new ImageUploadFailedException();
            }
        }).toList();

        ingredientTrimmingRowRepository.saveAll(ingredientTrimmingRows);

        return ResponseEntity.ok(null);
    }
}
