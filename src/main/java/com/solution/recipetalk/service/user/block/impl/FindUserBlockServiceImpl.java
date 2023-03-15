package com.solution.recipetalk.service.user.block.impl;

import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import com.solution.recipetalk.service.user.block.FindUserBlockService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindUserBlockServiceImpl implements FindUserBlockService {

    private final UserBlockRepository userBlockRepository;

    @Override
    public ResponseEntity<?> findUserBlockUserList(Pageable pageable) {
        Long sessionId = ContextHolder.getUserLoginId();
        Page<UserSimpleProfileDTO> data = userBlockRepository.findByUserId(sessionId, pageable);
        return ResponseEntity.ok(data);
    }
}
