package com.solution.recipetalk.dto.board;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private UserSimpleProfileDTO writer;
    private String title;
    private Long viewCounts;

    public static BoardDTO toDTO(Board board, UserSimpleProfileDTO writerProfileDto) {
        return BoardDTO.builder()
                .title(board.getTitle())
                .writer(writerProfileDto)
                .viewCounts(board.getView_count())
                .build();
    }
}
