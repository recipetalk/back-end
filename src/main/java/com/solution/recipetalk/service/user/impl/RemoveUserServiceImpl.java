package com.solution.recipetalk.service.user.impl;

import com.solution.recipetalk.domain.board.bookmark.repository.BookmarkRepository;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.fcm.repository.FcmTokenRepository;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.domain.notification.repository.NotificationRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.service.user.RemoveUserService;
import com.solution.recipetalk.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class RemoveUserServiceImpl implements RemoveUserService {

    private final UserDetailRepository userDetailRepository;
    private final UserBlockRepository userBlockRepository; // 삭제 필요 -> 차단된 사용자면 회원탈퇴 가능하게 해야 할까?
    private final CommentRepository commentRepository; // => 어차피 삭제된 유저는 조회 안되게 해놓음.
    private final BoardRepository boardRepository; // soft delete
    private final UserHasIngredientRepository userHasIngredientRepository; // 삭제 필요
    private final RecipeRepository recipeRepository; // board 삭제되면 어차피 안보임
    private final RecipeRowRepository recipeRowRepository; //이하동문
    private final IngredientTrimmingRepository ingredientTrimmingRepository; //이하동문
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository; //이하동문
    private final NotificationRepository notificationRepository; //삭제필요
    private final BoardLikeRepository boardLikeRepository; // 궂이?
    private final BookmarkRepository bookmarkRepository; // 삭제 필요.
    private final UserFollowRepository userFollowRepository; // 삭제 필요
    private final FcmTokenRepository fcmTokenRepository; // 삭제 필요

    @Override
    public ResponseEntity<?> removeUserDetail() {
        Long userLoginId = ContextHolder.getUserLoginId();
        UserDetail loginUser = userDetailRepository.findById(userLoginId).orElseThrow(UserNotFoundException::new);

        userDetailRepository.delete(loginUser);


        userHasIngredientRepository.deleteAllByUser_Id(userLoginId);
        notificationRepository.deleteAllByUser_Id(userLoginId);

        bookmarkRepository.deleteAllByUser_Id(userLoginId);

        userFollowRepository.deleteAllByUser_Id(userLoginId);
        userFollowRepository.deleteAllByFollowing_Id(userLoginId);

        fcmTokenRepository.deleteAllByUser_Id(userLoginId);

        return ResponseEntity.ok(null);
    }

}
