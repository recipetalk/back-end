package com.solution.recipetalk.service.board.like.impl;


import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.entity.BoardLike;
import com.solution.recipetalk.domain.board.like.id.BoardLikeId;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.board.BoardLikedDTO;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.service.board.like.ToggleBoardLikeService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ToggleBoardLikeServiceImpl implements ToggleBoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final UserDetailRepository userDetailRepository;

    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> toggleBoardLike(Long boardId) {
        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());

        Board findBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        BoardLikeId boardLikeId = new BoardLikeId(session, findBoard);
        Optional<BoardLike> find = boardLikeRepository.findById(boardLikeId);

        if(find.isPresent()){
            boardLikeRepository.delete(find.get());
            return ResponseEntity.ok(BoardLikedDTO.builder().isLiked(false).build());
        }
        else{
            BoardLike boardLike = BoardLike.builder().board(findBoard).user(session).build();
            boardLikeRepository.save(boardLike);
            return ResponseEntity.ok(BoardLikedDTO.builder().isLiked(true).build());
        }
    }
}
