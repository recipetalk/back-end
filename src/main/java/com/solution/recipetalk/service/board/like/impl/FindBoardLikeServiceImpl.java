package com.solution.recipetalk.service.board.like.impl;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.id.BoardLikeId;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.board.BoardLikedDTO;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.service.board.like.FindBoardLikeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindBoardLikeServiceImpl implements FindBoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final UserDetailRepository userDetailRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> findBoardLikeByBoardId(Long boardId) {

        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());
        Board findBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        BoardLikeId boardLikeId = new BoardLikeId(session, findBoard);

        BoardLikedDTO dto = new BoardLikedDTO(boardLikeRepository.findById(boardLikeId).isPresent());

        return ResponseEntity.ok(dto);
    }
}
