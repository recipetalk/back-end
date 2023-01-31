package com.solution.recipetalk.dto.comment;

import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentModifyDTO {

    @NonNull
    @Size(min = 1, max = 1000, message = "댓글은 1000자를 넘어가면 안됩니다.")
    private String description;
}
