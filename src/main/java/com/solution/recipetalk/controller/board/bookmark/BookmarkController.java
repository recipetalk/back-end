package com.solution.recipetalk.controller.board.bookmark;

import com.solution.recipetalk.domain.common.SortType;
import com.solution.recipetalk.service.board.bookmark.FindBookmarkService;
import com.solution.recipetalk.service.board.bookmark.ToggleBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookmarkController {
    private final FindBookmarkService findBookmarkService;
    private final ToggleBookmarkService toggleBookmarkService;

    @PostMapping("/board/{boardId}/bookmark")
    public ResponseEntity<?> actionBookmark(@PathVariable(name = "boardId") Long boardId){
        return toggleBookmarkService.toggleBookmark(boardId);
    }

    @GetMapping("/board/{boardId}/bookmark")
    public ResponseEntity<?> isBookmark(@PathVariable(name = "boardId") Long boardId){
        return findBookmarkService.findBookmarkByBoardId(boardId);
    }

    @GetMapping("/user/bookmark/boardList")
    public ResponseEntity<?> boardLists(@RequestParam(name = "sortType") SortType sortType, Pageable pageable) {
        return findBookmarkService.findBoardListByUserId(pageable, sortType);
    }
}
