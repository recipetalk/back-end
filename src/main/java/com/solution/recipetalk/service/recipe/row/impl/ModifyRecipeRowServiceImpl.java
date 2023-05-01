package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.img.entity.RecipeRowImg;
import com.solution.recipetalk.domain.recipe.row.img.repository.RecipeRowImgRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTO;
import com.solution.recipetalk.dto.recipe.row.RecipeRowModifyDTOWrapper;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.row.ModifyRecipeRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.ion.IonException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyRecipeRowServiceImpl implements ModifyRecipeRowService {
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeRowImgRepository recipeRowImgRepository;
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> modifyRecipeRow(Long recipeId, RecipeRowModifyDTOWrapper wrapper){
        Long loginUserId = ContextHolder.getUserLoginId();
        List<RecipeRowModifyDTO> dtoList = wrapper.getDtoList();

        if (dtoList.size() > 0){
            // TODO: Exception (NotFoundRecipeRowException)
            Long recipeRowUserId = recipeRowRepository.findById(wrapper.getDtoList().get(0).getRowSeq()).orElseThrow()
                    .getRecipe().getBoard().getWriter().getId();

            // TODO: Exception (수정 권한 검증)
            if (!Objects.equals(loginUserId, recipeRowUserId)){
                throw new RuntimeException("수정 권한이 없습니다.");
            }
        }

        dtoList.forEach(recipeRowModifyDTO -> {
            // TODO: Exception (NotFoundRecipeRowException)
            RecipeRow recipeRow = recipeRowRepository.findById(recipeRowModifyDTO.getRowSeq()).orElseThrow();

            List<String> imgUriList = recipeRowImgRepository.findImageURIByRecipeRowId(recipeRow.getId());
            List<Long> imgIdList = recipeRowImgRepository.findImageIdByRecipeRowId(recipeRow.getId());
            List<Image> imgs = imageRepository.findAllById(imgIdList);
            List<String> newImgUri = new ArrayList<>();

            // 이미지 업로드 및 recipeRow 수정
            recipeRowModifyDTO.getImgs().forEach(img -> {
                try{
                    newImgUri.add(s3Uploader.upload(img, S3dir.RECIPE_ROW_IMG_DIR));
                }catch (IOException e){
                    throw new ImageUploadFailedException();
                }
            });


            // 업로드 정상 확인
            if (newImgUri.size() != imgs.size()){
                // TODO: Exception
                throw new RuntimeException("파일 업로드 중 문제가 발생하였습니다. 재시도해주세요.");
            }

            // 기존 이미지 s3 삭제
            imgUriList.forEach(uri -> s3Uploader.deleteFile(uri, S3dir.RECIPE_ROW_IMG_DIR));

            // 이미지 uri 수정
            for (int i = 0; i < imgs.size(); i++){
                imgs.get(i).changeUri(newImgUri.get(i));
            }

            // row description, timer 수정
            recipeRow.changeDescription(recipeRowModifyDTO.getDescription());
            recipeRow.changeTimer(recipeRowModifyDTO.getTimer());
        });


        return null;
    }
}