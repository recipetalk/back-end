package com.solution.recipetalk.service.recipe.row.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.image.entity.Image;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.img.entity.RecipeRowImg;
import com.solution.recipetalk.domain.recipe.row.img.repository.RecipeRowImgRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.dto.recipe.row.RecipeRowRegisterDTO;
import com.solution.recipetalk.exception.common.NotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.recipe.row.RegisterRecipeRowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RegisterRecipeRowServiceImpl implements RegisterRecipeRowService {

    private final RecipeRowRepository recipeRowRepository;

    private final RecipeRepository recipeRepository;

    private final RecipeRowImgRepository recipeRowImgRepository;

    private final ImageRepository imageRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> registerRecipeRow(Long recipeId, List<RecipeRowRegisterDTO> dtos) {
        Recipe findRecipe = recipeRepository.findById(recipeId).orElseThrow(NotFoundException::new);

        List<RecipeRow> newRecipeRows = dtos.stream().map(dto -> dto.toRecipeRowEntity(findRecipe)).toList();

        recipeRowRepository.saveAll(newRecipeRows);

        for(int i = 0 ; i < dtos.size(); i++ ){
            if( null != dtos.get(i).getImgs()){
                List<MultipartFile> beforeUploadImg = dtos.get(i).getImgs();
                for(int j = 0 ; j < beforeUploadImg.size(); j++){
                    try {
                        String imgUrl = s3Uploader.upload(beforeUploadImg.get(i), S3dir.RECIPE_ROW_IMG_DIR);

                        Image image = Image.builder()
                                .URI(imgUrl)
                                .build();

                        imageRepository.save(image);

                        RecipeRowImg recipeRowImg = RecipeRowImg.builder()
                                .recipeRow(newRecipeRows.get(i))
                                .image(image)
                                .build();

                        recipeRowImgRepository.save(recipeRowImg);

                    } catch (IOException e) {
                        //TODO : 차후에 매핑 되지 않은 녀석들을 Batch로 날리는 작업 필요할 것 같음.
                        throw new ImageUploadFailedException();
                    }
                }
            }

        }

        return ResponseEntity.ok(null);
    }
}
