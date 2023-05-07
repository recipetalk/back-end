package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.annotation.CustomLength;
import com.solution.recipetalk.domain.common.SortType;
import lombok.*;

import java.io.Serializable;

// TODO: enum validate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeByUserReqDTO implements Serializable {
    @CustomLength(max = 10, message = "sortType")

    private SortType sortType;
    private long offset;
    private long limit;
}
