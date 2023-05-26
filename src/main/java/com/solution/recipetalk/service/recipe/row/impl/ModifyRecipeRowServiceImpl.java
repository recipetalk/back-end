package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowDTO;
import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTO;
import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTOWrapper;
import com.solution.recipetalk.exception.common.NotAuthorizedException;
import com.solution.recipetalk.exception.common.NotAuthorizedToModifyException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.exception.recipe.row.RecipeRowNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.row.ModifyRecipeRowService;
import com.solution.recipetalk.service.recipe.row.RegisterRecipeRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyRecipeRowServiceImpl implements ModifyRecipeRowService {
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeRepository recipeRepository;
    private final S3Uploader s3Uploader;
    private final RegisterRecipeRowService registerRecipeRowService;
    @Override
    public ResponseEntity<?> modifyRecipeRow(Long recipeId, RecipeRowModifyDTO dto){
        Recipe findRecipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);

        //권한 체킹
        if(recipeRepository.existsRecipeByBoardWriter_IdAndId(ContextHolder.getUserLoginId(), recipeId)){
            throw new NotAuthorizedException();
        }
        //레시피 Seq로 찾아오는 로직 필요
        //이 경우에는 찾아온 녀석 entity 삭제 후 dto.id 찾아와서 등록 (

        Optional<RecipeRow> recipeRowByRecipe_idAndSeqNum = recipeRowRepository.findRecipeRowByRecipe_IdAndSeqNum(recipeId, dto.getSeqNum());

        //나보다 큰 녀석은 모두 삭제.
        if(dto.getIsLast()){
            List<RecipeRow> recipeRowsByRecipeIdAndSeqNum = recipeRowRepository.findRecipeRowsByRecipeIdAndSeqNum(recipeId, dto.getSeqNum());

            recipeRowsByRecipeIdAndSeqNum.forEach(this::imageDelete);

            if(recipeRowsByRecipeIdAndSeqNum.size() > 0){
                recipeRowRepository.deleteAll(recipeRowsByRecipeIdAndSeqNum);
            }
        }

        if (recipeRowByRecipe_idAndSeqNum.isPresent()){
            if (dto.getId() != null) {
                RecipeRow dtoRecipeRow = recipeRowRepository.findById(dto.getId()).orElseThrow(RecipeRowNotFoundException::new);
                RecipeRow repoRecipeRow = recipeRowByRecipe_idAndSeqNum.get();

                imageDelete(repoRecipeRow);

                recipeRowRepository.delete(repoRecipeRow);

                if(dto.getImg() != null) {
                    imageDelete(dtoRecipeRow);
                    uploadImage(dto.getImg(), dtoRecipeRow);
                }else {
                    dtoRecipeRow.updateImageURI(dto.getImgUri());
                }

                dtoRecipeRow.updateSeqNum(dto.getSeqNum());
                dtoRecipeRow.updateDescription(dto.getDescription());
            }else {
                RecipeRow repoRecipeRow = recipeRowByRecipe_idAndSeqNum.get();

                imageDelete(repoRecipeRow);

                if(dto.getImg() != null) {
                    uploadImage(dto.getImg(), repoRecipeRow);
                }

                repoRecipeRow.updateSeqNum(dto.getSeqNum());
                repoRecipeRow.updateDescription(dto.getDescription());
            }
        }else {
            if(dto.getId() != null) {
                RecipeRow dtoRecipeRow = recipeRowRepository.findById(dto.getId()).orElseThrow(RecipeRowNotFoundException::new);

                if(dto.getImg() != null) {
                    imageDelete(dtoRecipeRow);
                    uploadImage(dto.getImg(), dtoRecipeRow);
                }else {
                    dtoRecipeRow.updateImageURI(dto.getImgUri());
                }

                dtoRecipeRow.updateSeqNum(dto.getSeqNum());
                dtoRecipeRow.updateDescription(dto.getDescription());
            }else{
                registerRecipeRowService.registerRecipeRow(findRecipe, dto.toRegisterDTO());
            }
        }

        //Seq가 없다면 dto.Id로 Entity 찾아오는 로직 필요
        //이럴 경우엔 Seq를 변경하는 로직 추가. (이미지가 멀티파트다. 그럼 등록. isDelete가 true다. 그럼 삭제)


        //마지막 시퀀스면 나머지 다 삭제하는 로직 필요



        return ResponseEntity.ok(null);
    }

    private void imageDelete(RecipeRow recipeRow){
        if(recipeRow.getImageURI() != null){
            s3Uploader.deleteFile(recipeRow.getImageURI(), S3dir.RECIPE_ROW_IMG_DIR);
            recipeRow.updateImageURI(null);
        }
    }

    private void uploadImage(MultipartFile file, RecipeRow entity){
        try {
            String uri = s3Uploader.upload(file, S3dir.RECIPE_ROW_IMG_DIR);
            entity.updateImageURI(uri);
        } catch (IOException e) {
            throw new ImageUploadFailedException();
        }
    }
}

