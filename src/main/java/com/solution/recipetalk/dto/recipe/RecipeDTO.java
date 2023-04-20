package com.solution.recipetalk.dto.recipe;

import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.dto.board.BoardDTO;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDTO {
    private BoardDTO board;
    private String description;
    private String thumbnailUri;
    private String quantity;

    public static RecipeDTO toDTO(Recipe recipe, BoardDTO boardDTO){
        return RecipeDTO.builder()
                .board(boardDTO)
                .description(recipe.getDescription())
                .quantity(recipe.getQuantity()+"인분")
                .thumbnailUri(recipe.getThumbnailImgURI())
                .build();
    }

    public RecipeDTO(Recipe recipe, UserDetail userDetail, Board board, Boolean isFollowing, Boolean isLiked, Boolean isBookmarked){

        UserSimpleProfileDTO userSimpleProfileDTO = UserSimpleProfileDTO.builder()
                .profileImageURI(userDetail.getProfileImageURI())
                .isFollowing(isFollowing)
                .nickname(userDetail.getNickname())
                .username(userDetail.getUsername())
                .description(userDetail.getDescription())
                .build();

        this.board = BoardDTO.toDTO(board, userSimpleProfileDTO, isLiked, isBookmarked);
        this. description = recipe.getDescription();
        this.quantity = recipe.getQuantity().toString();
        this.thumbnailUri = recipe.getThumbnailImgURI();

    }
}
