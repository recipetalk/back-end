package com.solution.recipetalk.service.board.impl;

import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.service.board.FindBoardByBoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindBoardByBoardLikeServiceImpl implements FindBoardByBoardLikeService {

    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> findBoardListByBoardLike(Pageable pageable) {

        return null;
    }
}
