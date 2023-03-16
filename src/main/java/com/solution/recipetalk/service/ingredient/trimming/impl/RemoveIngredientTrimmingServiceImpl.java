package com.solution.recipetalk.service.ingredient.trimming.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.RemoveIngredientTrimmingService;
import com.solution.recipetalk.service.ingredient.trimming.row.RemoveIngredientTrimmingRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class RemoveIngredientTrimmingServiceImpl implements RemoveIngredientTrimmingService {
    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final S3Uploader s3Uploader;
    private final UserDetailRepository userDetailRepository;
    private final RemoveIngredientTrimmingRowService removeIngredientTrimmingRowService;

    @Override
    public ResponseEntity<?> removeIngredientTrimmingById(Long trimmingId){
        Long userLoginId = ContextHolder.getUserLoginId();
        UserDetail currentUser = userDetailRepository.findById(userLoginId).orElseThrow(UserNotFoundException::new);
        
        IngredientTrimming ingredientTrimming = ingredientTrimmingRepository.findById(trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);
        if (!ingredientTrimming.getBoard().getWriter().equals(currentUser)){
            throw new CustomException(ErrorCode.NOT_AUTHORIZED);
        }
        
        String ingredientTrimmingUri = ingredientTrimming.getThumbnailUri();

        s3Uploader.deleteFile(ingredientTrimmingUri, S3dir.INGREDIENT_TRIMMING_IMG_DIR);
        removeIngredientTrimmingRowService.removeIngredientTrimmingRowByIngredientTrimming(ingredientTrimming);

        ingredientTrimmingRepository.delete(ingredientTrimming);

        return ResponseEntity.ok(null);
    }
}
