package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.img.entity.RecipeRowImg;
import com.solution.recipetalk.domain.recipe.row.img.repository.RecipeRowImgRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.row.RemoveRecipeRowService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveRecipeRowServiceImpl implements RemoveRecipeRowService {
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeRowImgRepository recipeRowImgRepository;
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> removeRecipeRow(Long recipeId, Long recipeRowId){
        Long loginUserId = ContextHolder.getUserLoginId();
        // TODO: exception(NotFoundRecipeRowException)
        RecipeRow recipeRow = recipeRowRepository.findById(recipeRowId).orElseThrow();

        if (!Objects.equals(loginUserId, recipeRow.getRecipe().getBoard().getWriter().getId())){
            // TODO: exception(삭제 권한 검증 실패)
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        List<RecipeRowImg> recipeRowImgs = recipeRowImgRepository.findAllByRecipeRowId(recipeRow.getId());
        List<Long> imgIds = recipeRowImgRepository.findImageIdByRecipeRowId(recipeRow.getId());
        List<Image> imgs = imageRepository.findAllById(imgIds);

        // S3 이미지 삭제
        imgs.forEach(img -> s3Uploader.deleteFile(img.getURI(), S3dir.RECIPE_ROW_IMG_DIR));

        // recipe row, recipe row img, image 삭제
        recipeRowImgRepository.deleteAll(recipeRowImgs);
        imageRepository.deleteAll(imgs);
        recipeRowRepository.delete(recipeRow);

        return null;
    }
}
