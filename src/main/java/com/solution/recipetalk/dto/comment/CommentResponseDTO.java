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
    private String createdDate;
    private boolean isModified;

    public static CommentResponseDTO toResponse(Comment comment) {
        return CommentResponseDTO.builder()
                .userName(comment.getWriter().getNickname())
                .description(comment.getDescription())
                .createdDate(comment.getCreatedDate().toString())
                .isModified(!comment.getModifiedDate().toString().equals(comment.getCreatedDate().toString()))
                .build();
    }
}
