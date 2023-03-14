package com.solution.recipetalk.controller.board.bookmark;

import com.solution.recipetalk.service.board.bookmark.FindBookmarkService;
import com.solution.recipetalk.service.board.bookmark.ToggleBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/board/{boardId}/bookmark")
public class BookmarkController {
    private final FindBookmarkService findBookmarkService;
    private final ToggleBookmarkService toggleBookmarkService;

    @PostMapping("")
    public ResponseEntity<?> actionBookmark(@PathVariable(name = "boardId") Long boardId){
        return toggleBookmarkService.toggleBookmark(boardId);
    }

    @GetMapping("")
    public ResponseEntity<?> isBookmark(@PathVariable(name = "boardId") Long boardId){
        return findBookmarkService.findBookmarkByBoardId(boardId);
    }
}
