package com.solution.recipetalk.service.ingredient.trimming.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingResult;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingDetailResult;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingFindResDTO;
import com.solution.recipetalk.exception.CustomException;
import com.solution.recipetalk.exception.ErrorCode;
import com.solution.recipetalk.exception.ingredient.trimming.IngredientTrimmingNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.ingredient.trimming.FindIngredientTrimmingService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindIngredientTrimmingServiceImpl implements FindIngredientTrimmingService {

    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserBlockRepository userBlockRepository;

    @Override
    public ResponseEntity<?> findIngredientTrimming(Long ingredientId, Pageable pageable){
        UserDetail currentUser = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);

        Page<IngredientTrimmingResult> ingredientTrimmingResults = ingredientTrimmingRepository
                .findIngredientTrimmingResultByIdExceptBlockedUser(ingredientId, currentUser, pageable).orElseThrow(IngredientTrimmingNotFoundException::new);

        return ResponseEntity.ok(ingredientTrimmingResults);
    }

    @Override
    public ResponseEntity<?> findIngredientTrimmingDetail(Long ingredientId, Long trimmingId){
        UserDetail currentUser = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);
        IngredientTrimmingDetailResult ingredientTrimmingDetail = ingredientTrimmingRepository.findIngredientTrimmingDetailResultById(ingredientId, trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new);
        if (userBlockRepository.existsByUserAndBlockedUser(ingredientTrimmingDetail.getBoard().getWriter(), currentUser)){
            // 권한이 없다는 것 보다는 차단한 유저에게 마치 원래 게시글이 없는 것처럼 보내줄 예정
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }


        List<IngredientTrimmingRow> trimmingRows = ingredientTrimmingRowRepository
                .findAllByIngredientTrimming(ingredientTrimmingRepository
                        .findById(trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new));

        return ResponseEntity.ok(IngredientTrimmingFindResDTO.fromIngredientTrimmingDetailResultAndTrimmingRows(ingredientTrimmingDetail, trimmingRows));
    }



}
