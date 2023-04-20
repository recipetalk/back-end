package com.solution.recipetalk.service.user.block.impl;

import com.solution.recipetalk.domain.user.block.entity.UserBlock;
import com.solution.recipetalk.domain.user.block.id.UserBlockId;
import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.user.UserBlockRemoveDTO;
import com.solution.recipetalk.exception.user.UserBlockNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.block.RemoveUserBlockService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveUserBlockServiceImpl implements RemoveUserBlockService {
    private final UserBlockRepository userBlockRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> removeUserBlock(UserBlockRemoveDTO dto) {
        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());
        UserDetail blockedUser = userDetailRepository.findUserDetailByUsername(dto.getBlockedUsername()).orElseThrow(UserNotFoundException::new);
        System.out.println(dto.getBlockedUsername());
        System.out.println(blockedUser);
        UserBlock findById = userBlockRepository.findByUserAndBlockedUser(session,blockedUser).orElseThrow(UserBlockNotFoundException::new);

        userBlockRepository.delete(findById);

        return ResponseEntity.ok(null);
    }
}
