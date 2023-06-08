package com.solution.recipetalk.dto.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solution.recipetalk.domain.board.entity.BoardSort;
import com.solution.recipetalk.dto.user.UserSimpleProfileDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardFacadeDTO {
    private UserSimpleProfileDTO writer;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private Boolean isLiked;
    private Boolean isBookmarked;
    private Long commentCount;
    private Long likeCount;
    private Long boardId;
    private BoardSort boardSort;
    private String thumbnailUri;
    private Long ingredientId;

    public BoardFacadeDTO(String title, String description, LocalDateTime createdDate, Boolean isLiked, Long commentCount, Long likeCount, Long boardId, BoardSort boardSort, String thumbnailURI, Long ingredientId){
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.isLiked = isLiked;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.boardId = boardId;
        this.boardSort = boardSort;
        this.thumbnailUri = thumbnailURI;
        this.ingredientId = ingredientId;
    }

    public BoardFacadeDTO(String title, String description, LocalDateTime createdDate, Boolean isBookmarked, Long commentCount, Long likeCount, Long boardId, BoardSort boardSort, String thumbnailURI, Long ingredientId, Boolean dummyBoolean){
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.isBookmarked = isBookmarked;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.boardId = boardId;
        this.boardSort = boardSort;
        this.thumbnailUri = thumbnailURI;
        this.ingredientId = ingredientId;
    }
}


/*

[
	{
		"board": {
			"writer": {
				"nickname": "닉네임",
				"username": "test",
				"description": "4아이 엄마^^"
				"profileURI": "http://blablablabla",
				"isFollowing" : true/false,
			},
			"title": "[찌개요리]자취 8년차 된장찌개 맛있게 끓이는 법"
			"description": "안녕하세여. 자취 8년차의 특별한 부대찌개 레시피를. 공개합니다.",
			"createdDate": "2022-06-04T....",
			"isLiked": true,
			"isBookmarked": true/false,
			"commentCount": 54,
			"likeCount": 54,
			"boardId": 1,
			"boardSort": "RECIPE",
		},
		"recipe": {
			"quantity": "4인분",
			"recipeId": 1,
			"thumbnailURI": "http://...",
		}
	},
	{
		"board": {
			"writer": {
				"nickname": "닉네임",
				"username": "test",
				"description": "4아이 엄마^^"
				"profileURI": "http://blablablabla",
				"isFollowing" : true/false,
			},
			"title": "[찌개요리]자취 8년차 된장찌개 맛있게 끓이는 법"
			"description": "안녕하세여. 자취 8년차의 특별한 부대찌개 레시피를. 공개합니다.",
			"createdDate": "2022-06-04T....",
			"isLiked": true,
			"isBookmarked": true/false,
			"commentCount": 54,
			"likeCount": 54,
			"boardId": 1,
			"boardSort": "TRIMMING",
		},
		"trimming": {
			"trimmingId": 1,
			"descirption": "balblabalbal"
		}
//효능(식재료 설명)은 좋아요 및 댓글 정보 없음.
]

 */
