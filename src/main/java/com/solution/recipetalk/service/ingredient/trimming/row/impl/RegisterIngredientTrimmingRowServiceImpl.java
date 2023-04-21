package com.solution.recipetalk.service.ingredient.trimming.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowRegisterDTO;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.row.RegisterIngredientTrimmingRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterIngredientTrimmingRowServiceImpl implements RegisterIngredientTrimmingRowService {

    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final S3Uploader s3Uploader;
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;
    private final UserDetailRepository userDetailRepository;


    @Override
    public ResponseEntity<?> registerIngredientTrimmingRow(IngredientTrimmingRowRegisterDTO dto, Long ingredientTrimmingId) {
        IngredientTrimming findIngredientTrimming = ingredientTrimmingRepository.findById(ingredientTrimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);
        UserDetail currentUser = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);

        if (!findIngredientTrimming.getBoard().getWriter().equals(currentUser)){
            throw new CustomException(ErrorCode.NOT_AUTHORIZED);
        }

        try {
            IngredientTrimmingRow createdIngredientTrimmingRows = dto.toIngredientTrimmingRow(findIngredientTrimming,
                    dto.getImg() != null ? s3Uploader.upload(dto.getImg(), S3dir.INGREDIENT_TRIMMING_ROW_IMG_DIR) : null);
            ingredientTrimmingRowRepository.save(createdIngredientTrimmingRows);
        } catch (IOException e) {
            throw new ImageUploadFailedException();
        }


        return ResponseEntity.ok(null);
    }
}
