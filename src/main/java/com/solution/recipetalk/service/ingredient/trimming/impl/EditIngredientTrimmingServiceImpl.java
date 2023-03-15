package com.solution.recipetalk.service.ingredient.trimming.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingModifyDTO;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.ingredient.trimming.EditIngredientTrimmingService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class EditIngredientTrimmingServiceImpl implements EditIngredientTrimmingService {
    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final BoardRepository boardRepository;
    private final UserDetailRepository userDetailRepository;
    private final S3Uploader s3Uploader;

    public ResponseEntity<?> editIngredientTrimming(IngredientTrimmingModifyDTO dto, Long trimmingId) {
        UserDetail currentUser = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);
        IngredientTrimming ingredientTrimming = ingredientTrimmingRepository.findById(trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);
        if (!ingredientTrimming.getBoard().getWriter().equals(currentUser)){
           throw new CustomException(ErrorCode.NOT_AUTHORIZED_TO_MODIFY);
        }

        Board board = boardRepository.findById(ingredientTrimming.getId()).orElseThrow(BoardNotFoundException::new);

        String dir = "";
        if( null != dto.getThumbnail() ){
            try {
                dir = s3Uploader.upload(dto.getThumbnail(), S3dir.INGREDIENT_TRIMMING_IMG_DIR);
            } catch (IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        board.changeTitle(dto.getTitle());
        ingredientTrimming.changeThumbnailUri(dir);
        ingredientTrimmingRepository.save(ingredientTrimming);
        boardRepository.save(board);

        return ResponseEntity.ok(null);
    }
}
