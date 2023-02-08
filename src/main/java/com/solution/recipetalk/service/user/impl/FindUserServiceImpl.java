package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.DuplicateUserDTO;
import com.solution.recipetalk.dto.user.UserDetailProfileDTO;
import com.solution.recipetalk.exception.signup.DuplicatedUserException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindUserServiceImpl implements FindUserService {

    private final UserLoginRepository userLoginRepository;

    private final UserDetailRepository userDetailRepository;

    private final UserFollowRepository userFollowRepository;

    @Override
    public ResponseEntity<?> findDuplicatedUsernameInUserLogin(String userName) {
        DuplicateUserDTO dto = DuplicateUserDTO.builder().isValid(isDuplicatedUsernameExceptionHandler(userName)).build();
        return ResponseEntity.ok(dto);
    }

    private Boolean isDuplicatedUsernameExceptionHandler(String userName){
        Boolean isOk = isDuplicatedUsername(userName);
        if(!isOk){
            throw new DuplicatedUserException();
        }
        return true;
    }

    private Boolean isDuplicatedUsername(String userName){
        Optional<UserLogin> optionalUserLogin = userLoginRepository.findByUsername(userName);
        return optionalUserLogin.isEmpty();
    }

    @Override
    public ResponseEntity<?> findUserProfile(String username) {
        UserLogin findUserLogin = userLoginRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        UserDetail findUserDetail = findUserLogin.getUserDetail();

        Long followCount = userFollowRepository.countByFollowee(findUserDetail);

        UserDetailProfileDTO findUserProfileDTO = UserDetailProfileDTO.toDTO(findUserLogin, findUserDetail, followCount);

        return ResponseEntity.ok(findUserProfileDTO);
    }
}
