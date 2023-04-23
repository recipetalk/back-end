package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.UserDetailProfileModifyDTO;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.user.ModifyUserDetailService;
import com.solution.recipetalk.service.user.RemoveUserService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class RemoveUserServiceImpl implements RemoveUserService {

    private final UserDetailRepository userDetailRepository;


    @Override
    public ResponseEntity<?> removeUserDetail() {
        Long userLoginId = ContextHolder.getUserLoginId();
        UserDetail loginUser = userDetailRepository.findById(userLoginId).orElseThrow(UserNotFoundException::new);

        userDetailRepository.delete(loginUser);


        return ResponseEntity.ok(null);
    }

}