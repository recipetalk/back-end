package com.solution.recipetalk.service.board.bookmark.impl;

import com.solution.recipetalk.domain.board.bookmark.id.BookmarkId;
import com.solution.recipetalk.domain.board.bookmark.repository.BookmarkRepository;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.id.BoardLikeId;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.board.BoardLikedDTO;
import com.solution.recipetalk.dto.board.BookmarkDTO;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.service.board.bookmark.FindBookmarkService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FindBookmarkServiceImpl implements FindBookmarkService {

    private final UserDetailRepository userDetailRepository;
    private final BoardRepository boardRepository;
    private final BookmarkRepository bookmarkRepository;
    @Override
    public ResponseEntity<?> findBookmarkByBoardId(Long boardId) {

        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());
        Board findBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        BookmarkId bookmarkId = new BookmarkId(session, findBoard);

        BookmarkDTO dto = new BookmarkDTO(bookmarkRepository.findById(bookmarkId).isPresent());

        return ResponseEntity.ok(dto);
    }
}
