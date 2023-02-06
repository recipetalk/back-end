package com.solution.recipetalk.dto.board;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.dto.comment.CommentInBoardResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardFindResponseDTO {
    private String nickname;
    private String title;
    private Long viewCount;
    private List<CommentInBoardResponseDTO> comments;

    public static BoardFindResponseDTO toResponse(Board board) {
        return BoardFindResponseDTO.builder()
                .nickname(board.getWriter().getNickname())
                .title(board.getTitle())
                .viewCount(board.getView_count())
                .comments(board.getComments().stream().map(CommentInBoardResponseDTO::toResponse).toList())
                .build();
    }
}
