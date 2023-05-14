package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.DuplicateUserDTO;
import com.solution.recipetalk.dto.user.UserDetailProfileDTO;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import com.solution.recipetalk.exception.signup.DuplicatedNicknameException;
import com.solution.recipetalk.exception.signup.DuplicatedUserException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.FindUserService;
import com.solution.recipetalk.util.ContextHolder;
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

    private final RecipeRepository recipeRepository;
    @Override
    public ResponseEntity<?> findDuplicatedUsernameInUserLogin(String userName) {
        DuplicateUserDTO dto = DuplicateUserDTO.builder().isValid(isDuplicatedUsernameExceptionHandler(userName)).build();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<?> findDuplicatedNicknameInUserLogin(String nickname) {
        DuplicateUserDTO dto = DuplicateUserDTO.builder().isValid(isDuplicatedNicknameExceptionHandler(nickname)).build();
        return ResponseEntity.ok(dto);
    }

    private Boolean isDuplicatedNicknameExceptionHandler(String nickname) {
        if(!isDuplicatedNickname(nickname)) {
            throw new DuplicatedNicknameException();
        }
        return true;
    }

    private Boolean isDuplicatedNickname(String nickname) {
        Optional<UserDetail> byNickname = userDetailRepository.findByNickname(nickname);
        return byNickname.isEmpty();
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
        UserDetail findUserDetail = userDetailRepository.findUserDetailByUsername(username).orElseThrow(UserNotFoundException::new);

        Long followingCount = userFollowRepository.countByUser(findUserDetail, ContextHolder.getUserLoginId());
        Long followerCount = userFollowRepository.countByFollowing(findUserDetail, ContextHolder.getUserLoginId());
        Long recipeNum = recipeRepository.countByBoard_Writer(findUserDetail);

        UserDetailProfileDTO findUserProfileDTO = UserDetailProfileDTO.toDTO(findUserDetail, followingCount, followerCount, recipeNum);

        return ResponseEntity.ok(findUserProfileDTO);
    }

    @Override
    public ResponseEntity<?> findMyProfile() {
        UserDetail session = userDetailRepository.findById(ContextHolder.getUserLoginId()).orElseThrow(UserNotFoundException::new);

        UserSimpleProfileDTO profileDTO = UserSimpleProfileDTO.builder()
                .nickname(session.getNickname())
                .description(session.getDescription())
                .profileImageURI(session.getProfileImageURI())
                .build();
        return ResponseEntity.ok(profileDTO);
    }
}
