package com.solution.recipetalk.dto.comment;

import com.solution.recipetalk.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private String userName;
    private String description;
    private String lastModifiedDate;

    public static CommentResponseDTO toResponse(Comment comment) {
        return CommentResponseDTO.builder()
                .userName(comment.getWriter().getNickname())
                .description(comment.getDescription())
                .lastModifiedDate(comment.getModifiedDate().toString())
                .build();
    }
}
