package com.solution.recipetalk.service.ingredient.trimming.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowModifyDTO;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.exception.ingredient.trimming.row.IngredientTrimmingRowNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.row.EditIngredientTrimmingRowService;
import com.solution.recipetalk.service.ingredient.trimming.row.RegisterIngredientTrimmingRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EditIngredientTrimmingRowServiceImpl implements EditIngredientTrimmingRowService {
    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;
    private final S3Uploader s3Uploader;
    private final UserDetailRepository userDetailRepository;
    private final RegisterIngredientTrimmingRowService registerIngredientTrimmingRowService;

    @Override
    public ResponseEntity<?> editIngredientTrimmingRow(IngredientTrimmingRowModifyDTO dto, Long trimmingId) {
        IngredientTrimming findIngredientTrimming = ingredientTrimmingRepository.findById(trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);

        //권한 체킹
        if(!ingredientTrimmingRepository.existsIngredientTrimmingByBoardWriter_IAndId(ContextHolder.getUserLoginId(), trimmingId)){
            throw new NotAuthorizedException();
        }

        Optional<IngredientTrimmingRow> byIngredientTrimmingAndTrimmingSeq = ingredientTrimmingRowRepository.findByIngredientTrimmingAndTrimmingSeq(trimmingId, dto.getTrimmingSeq());


        if(byIngredientTrimmingAndTrimmingSeq.isPresent()){
            if(dto.getId() != null){
                IngredientTrimmingRow dtoTrimmingRow = ingredientTrimmingRowRepository.findById(dto.getId()).orElseThrow(IngredientTrimmingRowNotFoundException::new);
                IngredientTrimmingRow repoTrimmingRow = byIngredientTrimmingAndTrimmingSeq.get();

                if(!dtoTrimmingRow.getTrimmingSeq().equals(repoTrimmingRow.getTrimmingSeq())) {
                    imageDelete(repoTrimmingRow);
                    ingredientTrimmingRowRepository.delete(repoTrimmingRow);
                }

                if(dto.getImg() != null){
                    imageDelete(dtoTrimmingRow);
                    uploadImage(dto.getImg(), dtoTrimmingRow);
                }else {
                    dtoTrimmingRow.changeImageURI(dto.getImgUri());
                }

                dtoTrimmingRow.changeTrimmingSeq(dto.getTrimmingSeq());
                dtoTrimmingRow.changeDescription(dto.getDescription());
            } else {
                IngredientTrimmingRow repoTrimmingRow = byIngredientTrimmingAndTrimmingSeq.get();

                imageDelete(repoTrimmingRow);

                if(dto.getImg() != null){
                    uploadImage(dto.getImg(), repoTrimmingRow);
                }

                repoTrimmingRow.changeTrimmingSeq(dto.getTrimmingSeq());
                repoTrimmingRow.changeDescription(dto.getDescription());
            }
        }else {
            if(dto.getId() != null){
                IngredientTrimmingRow dtoTrimmingRow = ingredientTrimmingRowRepository.findById(dto.getId()).orElseThrow(IngredientTrimmingRowNotFoundException::new);

                if(dto.getImg() != null){
                    imageDelete(dtoTrimmingRow);
                    uploadImage(dto.getImg(), dtoTrimmingRow);
                }else {
                    dtoTrimmingRow.changeImageURI(dto.getImgUri());
                }

                dtoTrimmingRow.changeTrimmingSeq(dto.getTrimmingSeq());
                dtoTrimmingRow.changeDescription(dto.getDescription());
            } else {
                registerIngredientTrimmingRowService.registerIngredientTrimmingRow(dto.toRegisterDTO(), findIngredientTrimming);
            }
        }

        if(dto.getIsLast()){
            List<IngredientTrimmingRow> ingredientTrimmingRows = ingredientTrimmingRowRepository.findIngredientTrimmingRowsByIngredientTrimming_IdAndTrimmingSeq(trimmingId, dto.getTrimmingSeq());

            ingredientTrimmingRows.forEach(this::imageDelete);

            if(ingredientTrimmingRows.size() > 0){
                ingredientTrimmingRowRepository.deleteAll(ingredientTrimmingRows);
            }
        }

        return ResponseEntity.ok(null);
    }

    private void imageDelete(IngredientTrimmingRow trimmingRow){
        if(trimmingRow.getImgURI() != null){
            s3Uploader.deleteFile(trimmingRow.getImgURI(), S3dir.RECIPE_ROW_IMG_DIR);
            trimmingRow.changeImageURI(null);
        }
    }

    private void uploadImage(MultipartFile file, IngredientTrimmingRow entity){
        try {
            String uri = s3Uploader.upload(file, S3dir.RECIPE_ROW_IMG_DIR);
            entity.changeImageURI(uri);
        } catch (IOException e) {
            throw new ImageUploadFailedException();
        }
    }
}

