package com.solution.recipetalk.dto.board;

import com.solution.recipetalk.domain.board.entity.Board;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardIngredientDTO {
    private Long id;
    private String title;
    private String nickname;
    private Long likeCount;
    private Long commentCount;

    public static BoardIngredientDTO toBoardIngredientDTO(Board board){
        return BoardIngredientDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .nickname(board.getWriter().getNickname())
                .likeCount(board.getLikeCount())
                .commentCount(board.getCommentCount())
                .build();
    }
}
