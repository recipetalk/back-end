package com.solution.recipetalk.service.board.bookmark;

import com.solution.recipetalk.domain.common.SortType;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;



public interface FindBookmarkService {

    ResponseEntity<?> findBookmarkByBoardId(Long boardId);

    ResponseEntity<?> findBoardListByUserId(Pageable pageable, SortType sortType);
}
