package com.solution.recipetalk.dto.ingredient.description;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDescriptionModifyDTO {
    private String title;
    private MultipartFile imgFile;
    private String description;
}
