package com.solution.recipetalk.service.board.bookmark.impl;

import com.solution.recipetalk.domain.board.bookmark.entity.Bookmark;
import com.solution.recipetalk.domain.board.bookmark.id.BookmarkId;
import com.solution.recipetalk.domain.board.bookmark.repository.BookmarkRepository;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.dto.board.BookmarkDTO;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.service.board.bookmark.ToggleBookmarkService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ToggleBookmarkServiceImpl implements ToggleBookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserDetailRepository userDetailRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<?> toggleBookmark(Long boardId) {
        UserDetail session = userDetailRepository.getReferenceById(ContextHolder.getUserLoginId());

        Board findBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        BookmarkId boardLikeId = new BookmarkId(session, findBoard);
        Optional<Bookmark> find = bookmarkRepository.findById(boardLikeId);

        if(find.isPresent()){
            bookmarkRepository.delete(find.get());
            return ResponseEntity.ok(BookmarkDTO.builder().isBookmarked(false).build());
        }
        else{
            Bookmark bookmark = Bookmark.builder().board(findBoard).user(session).build();
            bookmarkRepository.save(bookmark);
            return ResponseEntity.ok(BookmarkDTO.builder().isBookmarked(true).build());
        }
    }
}
