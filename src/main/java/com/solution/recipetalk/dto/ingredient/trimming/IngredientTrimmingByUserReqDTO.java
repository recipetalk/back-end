package com.solution.recipetalk.dto.ingredient.trimming;

import com.solution.recipetalk.annotation.CustomLength;
import com.solution.recipetalk.annotation.Enum;
import com.solution.recipetalk.domain.common.SortType;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowModifyDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTrimmingByUserReqDTO {
    @CustomLength(max = 10, message = "sortType")
    private SortType sortType;
    private Long offset;
    private Long limit;
}