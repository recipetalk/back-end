package com.solution.recipetalk.service.board.impl;

import com.solution.recipetalk.domain.board.bookmark.repository.BookmarkRepository;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.service.board.RemoveBoardService;
import com.solution.recipetalk.service.comment.RemoveCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveBoardServiceImpl implements RemoveBoardService {

    private final BoardRepository boardRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final RemoveCommentService removeCommentService;

    @Override
    public ResponseEntity<?> hardRemoveByBoardId(Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        bookmarkRepository.deleteAllByBoard_Id(boardId);

        boardLikeRepository.deleteAllByBoard_Id(boardId);


        removeCommentService.hardRemoveChildCommentsByBoardId(boardId);
        removeCommentService.hardRemoveParentCommentsByBoardId(boardId);

        boardRepository.hardDeleteBoardByboardId(boardId);

        return ResponseEntity.ok(null);
    }
}
