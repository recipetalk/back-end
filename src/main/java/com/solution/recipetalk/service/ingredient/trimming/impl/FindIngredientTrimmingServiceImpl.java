package com.solution.recipetalk.service.ingredient.trimming.impl;

import com.querydsl.core.Tuple;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingResult;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingDetailResult;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingByUserReqDTO;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingByUserResDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
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
    public ResponseEntity<?> findIngredientTrimmingDetail(Long trimmingId){
        UserDetail currentUser = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);
        IngredientTrimmingDetailResult ingredientTrimmingDetail = ingredientTrimmingRepository.findIngredientTrimmingDetailResultById(trimmingId, currentUser.getId()).orElseThrow(IngredientTrimmingNotFoundException::new);

        List<IngredientTrimmingRow> trimmingRows = ingredientTrimmingRowRepository
                .findAllByIngredientTrimming(ingredientTrimmingRepository
                        .findById(trimmingId).orElseThrow(IngredientTrimmingNotFoundException::new));

        return ResponseEntity.ok(IngredientTrimmingFindResDTO.fromIngredientTrimmingDetailResultAndTrimmingRows(ingredientTrimmingDetail, trimmingRows));
    }

    @Override
    public ResponseEntity<?> findIngredientTrimmingListByUsername(IngredientTrimmingByUserReqDTO dto, String username){
        UserDetail userDetail = userDetailRepository.findUserDetailByUsername(username).orElseThrow(UserNotFoundException::new);
        UserDetail loginUser = ContextHolder.getUserLogin().getUserDetail();

        if (userBlockRepository.existsByUserAndBlockedUser(loginUser, userDetail)){
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }
        List<Tuple> trimmings = ingredientTrimmingRepository.findIngredientTrimmingList(dto, userDetail.getId());

        return ResponseEntity.ok(trimmings.stream().map(IngredientTrimmingByUserResDTO::toIngredientTrimmingByUserResDTO).toList());
    }



}
