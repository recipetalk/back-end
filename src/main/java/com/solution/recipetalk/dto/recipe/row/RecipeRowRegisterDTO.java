package com.solution.recipetalk.dto.recipe.row;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRowRegisterDTO {
    @NonNull
    private Long boardId;
    @NonNull
    private String description;

    //TODO : File valid 기능 필요
    private List<MultipartFile> imgs;
    @NonNull
    private String timer;
}
