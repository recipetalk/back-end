package com.solution.recipetalk.dto.ingredient.trimming;

import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository.IngredientTrimmingDetailResult;
import com.solution.recipetalk.domain.ingredient.trimming.row.entity.IngredientTrimmingRow;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.board.BoardDTO;
import com.solution.recipetalk.dto.ingredient.trimming.row.IngredientTrimmingRowResDTO;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientTrimmingFindResDTO {
    List<IngredientTrimmingRowResDTO> trimmingRows;
    BoardDTO boardDTO;
    String description;
    String thumbnailURI;

    public static IngredientTrimmingFindResDTO fromIngredientTrimmingDetailResultAndTrimmingRows(IngredientTrimmingDetailResult result, List<IngredientTrimmingRow> trimmingRows){
        IngredientTrimmingFindResDTO dto = new IngredientTrimmingFindResDTO();
        UserSimpleProfileDTO userSimpleProfileDTO = UserSimpleProfileDTO.toDTO(result.getWriter(), result.getIsFollowed());
        BoardDTO boardDTO = BoardDTO.toDTO(result.getBoard(), userSimpleProfileDTO, result.getIsLiked(), result.getIsBookmarked());
        dto.setBoardDTO(boardDTO);
        dto.setDescription(result.getIngredientTrimming().getDescription());
        dto.setThumbnailURI(result.getIngredientTrimming().getThumbnailUri());
        dto.setTrimmingRows(trimmingRows.stream().map(IngredientTrimmingRowResDTO::fromTrimmingRow).toList());

        return dto;
    }

}
