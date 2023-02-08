package com.solution.recipetalk.service.board.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.board.BoardCreateDTO;
import com.solution.recipetalk.service.board.RegisterBoardService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterBoardServiceImpl implements RegisterBoardService {
    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final UserDetailRepository userDetailRepository;

    @Override
    public ResponseEntity<?> addBoard(BoardCreateDTO boardCreateDTO) {
        Long currentLoginUserId = ContextHolder.getUserLoginId();
        UserDetail writer = userDetailRepository.findById(currentLoginUserId).orElse(null);

        assert writer != null;
        Board newBoard = Board.builder()
                .writer(writer)
                .title(boardCreateDTO.getTitle())
                .view_count(boardCreateDTO.getViewCount())
                .boardLikes(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();

        boardRepository.save(newBoard);

        return ResponseEntity.ok(boardCreateDTO);
    }
}
