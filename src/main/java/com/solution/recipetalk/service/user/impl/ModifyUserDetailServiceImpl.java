package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.config.properties.S3dir;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.UserDetailProfileModifyDTO;
import com.solution.recipetalk.exception.s3.ImageUploadFailedException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.s3.upload.S3Uploader;
import com.solution.recipetalk.service.user.ModifyUserDetailService;
import com.solution.recipetalk.util.ContextHolder;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyUserDetailServiceImpl implements ModifyUserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<?> modifyUserDetail(UserDetailProfileModifyDTO dto) {
        Long userLoginId = ContextHolder.getUserLoginId();

        UserDetail loginDetail = userDetailRepository.findById(userLoginId).orElseThrow(UserNotFoundException::new);

        loginDetail.setNickname(dto.getNickname());
        loginDetail.setDescription(dto.getDescription());

        if (null != dto.getProfileImg()){
            try {
                //차후엔 로그관리 필요할 것 같음..
                if(loginDetail.getProfileImageURI() != null || loginDetail.getProfileImageURI().equals("")){
                    s3Uploader.deleteFile(loginDetail.getProfileImageURI(), S3dir.USER_PROFILE_IMG_DIR);
                }
                String profileURI = s3Uploader.upload(dto.getProfileImg(), S3dir.USER_PROFILE_IMG_DIR);
                loginDetail.setProfileImageURI(profileURI);
            } catch (IOException e) {
                throw new ImageUploadFailedException();
            }
        }

        return ResponseEntity.ok(null);
    }

}
