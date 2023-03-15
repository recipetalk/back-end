package com.solution.recipetalk.service.user.block.impl;

import com.solution.recipetalk.domain.user.block.entity.UserBlock;
import com.solution.recipetalk.domain.user.block.id.UserBlockId;
import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.UserBlockRegisterDTO;
import com.solution.recipetalk.exception.user.UserBlockExistException;
import com.solution.recipetalk.exception.user.UserBlockNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.block.RegisterUserBlockService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class RegisterUserBlockServiceImpl implements RegisterUserBlockService {

    private final UserBlockRepository userBlockRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> addUserBlock(UserBlockRegisterDTO dto) {
        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLogin().getId());

        UserDetail blockedUser = userDetailRepository.findUserDetailByUsername(dto.getBlockUsername()).orElseThrow(UserNotFoundException::new);

        UserBlock userBlock = UserBlock.builder().user(session).blockedUser(blockedUser).build();


        Optional<UserBlock> byId = userBlockRepository.findById(new UserBlockId(session, blockedUser));
        if(byId.isPresent()){
            throw new UserBlockExistException();
        }
        //이미 차단되어 있음. 처리 필요
        userBlockRepository.save(userBlock);

        return ResponseEntity.ok(null);
    }
}
