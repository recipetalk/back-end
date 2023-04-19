package com.solution.recipetalk.dto.board;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.dto.recipe.RecipeDTO;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private UserSimpleProfileDTO writer;
    private String title;
    private LocalDateTime createdDate;
    private Boolean isLiked;
    private Boolean isBookmarked;
    private Long likeCount;
    private Long commentCount;
    private Long boardId;

    public static BoardDTO toDTO(Board board, UserSimpleProfileDTO writerProfileDto) {
        return BoardDTO.builder()
                .title(board.getTitle())
                .createdDate(board.getCreatedDate())
                .writer(writerProfileDto)
                .boardId(board.getId())
                .build();
    }

    public static BoardDTO toDTO(Board board, UserSimpleProfileDTO writer, Boolean isLiked, Boolean isBookmarked, Long likeCount, Long commentCount) {
        return BoardDTO.builder()
                .boardId(board.getId())
                .writer(writer)
                .isLiked(isLiked)
                .isBookmarked(isBookmarked)
                .createdDate(board.getCreatedDate())
                .likeCount(likeCount)
                .commentCount(commentCount)
                .title(board.getTitle())
                .build();
    }
}
