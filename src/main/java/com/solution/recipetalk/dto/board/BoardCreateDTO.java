package com.solution.recipetalk.dto.board;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDTO {
    @NonNull
    @Size(min = 1, max = 100, message = "게시물 제목은 100자를 넘을 수 없습니다.")
    private String title;
    @NonNull
    @Builder.Default
    private Long viewCount = 0L;
}