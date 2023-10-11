package com.solution.recipetalk.listener;

import com.solution.recipetalk.domain.bill.repository.BillRepository;
import com.solution.recipetalk.domain.board.bookmark.entity.Bookmark;
import com.solution.recipetalk.domain.board.bookmark.id.BookmarkId;
import com.solution.recipetalk.domain.board.bookmark.repository.BookmarkRepository;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.entity.BoardSort;
import com.solution.recipetalk.domain.board.like.entity.BoardLike;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.entity.Comment;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.ingredient.description.entity.IngredientDescription;
import com.solution.recipetalk.domain.ingredient.description.repository.IngredientDescriptionRepository;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.ingredient.userhas.entity.IngredientState;
import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.domain.notification.entity.Notification;
import com.solution.recipetalk.domain.notification.repository.NotificationRepository;
import com.solution.recipetalk.domain.notification.state.NotificationSort;
import com.solution.recipetalk.domain.notification.state.NotificationState;
import com.solution.recipetalk.domain.product.entity.Product;
import com.solution.recipetalk.domain.product.repository.ProductRepository;
import com.solution.recipetalk.domain.recipe.entity.*;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.domain.report.repository.ReportRepository;
import com.solution.recipetalk.domain.user.block.entity.UserBlock;
import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.follow.entity.UserFollow;
import com.solution.recipetalk.domain.user.follow.repository.UserFollowRepository;
import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.entity.UserProvider;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.exception.comment.CommentNotFoundException;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import com.solution.recipetalk.vo.notification.comment.CommentNotificationVO;
import com.solution.recipetalk.vo.notification.ingredient.userhas.UserHasIngredientNotificationVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class DummyDataListener implements ApplicationListener<ContextRefreshedEvent> {

    private final BillRepository billRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final CommentRepository commentRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientTrimmingRepository ingredientTrimmingRepository;
    private final IngredientTrimmingRowRepository ingredientTrimmingRowRepository;
    private final IngredientDescriptionRepository ingredientDescriptionRepository;
    private final ImageRepository imageRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ReportRepository reportRepository;
    private final UserLoginRepository userLoginRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserBlockRepository userBlockRepository;
    private final UserFollowRepository userFollowRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ProductRepository productRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final NotificationRepository notificationRepository;
    private final UserHasIngredientRepository userHasIngredientRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUserData();
        loadTestUserData();
        loadBoardData();
        loadIngredientData();
        loadRecipeData();
        loadRecipeRowData();
        loadIngredientTrimmingData();
        //loadUserBlockData();
        loadBoardLikeData();
        loadUserFollowData();
        loadCommentData();
        loadBookmarkData();
        loadIngredientDescriptionData();
        loadProductData();
        loadNotificationData();
        loadUserHasIngredientData();
    }

    private void loadNotificationData(){
        createNotificationIfNotNull(1L, 1L, NotificationState.NOT_OPEN, "레시피톡", "테스트 알림1", CommentNotificationVO.toNavigationId(1L,1L, "RECIPE"), NotificationSort.COMMENT);
        createNotificationIfNotNull(2L, 1L, NotificationState.NOT_OPEN, "레시피톡", "테스트 알림2", CommentNotificationVO.toNavigationId(1L,1L, "RECIPE"), NotificationSort.COMMENT);
        createNotificationIfNotNull(3L, 1L, NotificationState.NOT_OPEN, "레시피톡", "테스트 알림3", CommentNotificationVO.toNavigationId(1L,1L, "RECIPE"), NotificationSort.COMMENT);
        createNotificationIfNotNull(4L, 1L, NotificationState.NOT_OPEN, "레시피톡", "테스트 알림4", CommentNotificationVO.toNavigationId(1L,1L, "RECIPE"), NotificationSort.COMMENT);

    }

    private void loadUserData() {
        //테스트를 위해 id : 2L로 시작함.
        createUserDataIfNotNull(1L, "hyunkim", "khj745700", "testtest", false, "rlaguswls1234@naver.com");
    }

    private void loadTestUserData() {
        createUserDataIfNotNull(2L, "test", "test", "test", false, "atene1408@gmail.com");
        createUserDataIfNotNull(3L, "test1", "test1", "test", true, "atene1408@naver.com");
        createUserDataIfNotNull(4L, "test2", "test2", "test", false, "ghdwlgns1234@naver.com");
    }

    private void loadBoardData() {
        createBoardData(1L, "test", "test board", 0L, BoardSort.RECIPE);
        createBoardData(2L, "hyunkim", "test board2", 0L, BoardSort.TRIMMING);
        createBoardData(3L, "test1", "test board3", 0L,BoardSort.DESCRIPTION);
        createBoardData(4L, "hyunkim", "test board4", 0L,BoardSort.RECIPE);
        for(long i = 5L; i <=25 ; i++){
            createBoardData(i, "hyunkim", Long.toString(i), 0L,BoardSort.RECIPE);
        }

    }

    private void loadIngredientData() {
        createTestIngredient("ingredient1");
        createTestIngredient("ingredient2");
    }

    private void loadRecipeData() {
        createRecipeDataIfNotNull(1L, "", 1L, "ONE", "sample");
        createRecipeDataIfNotNull(4L, "", 4L, "ONE", "sample");
        for(long i = 5L; i <=25 ; i++){
            createRecipeDataIfNotNull(i, "", i, "ONE", "sample");
        }
    }

    private void loadRecipeRowData() {
        createRecipeRowDataIfNotNull(1L, "kind of food", 2L, 1L);
        createRecipeRowDataIfNotNull(2L, "kind of recipe", 1L, 1L);
    }

    private void loadIngredientTrimmingData() {
        createIngredientTrimmingDataIfNotNull(2L, 1L, 2L);
    }

    private void loadUserFollowData() {
        createUserFollowIfNotNull(1L,1L, 2L);
        createUserFollowIfNotNull(2L,1L, 3L);
    }

    private void loadUserBlockData() {
        createUserBlockIfNotNull(1L,2L);
        createUserBlockIfNotNull(1L,3L);
    }


    private void loadBoardLikeData(){
        createBoardLikeIfNotNull(1L, 1L );
        createBoardLikeIfNotNull(2L, 1L);
        createBoardLikeIfNotNull(3L, 1L);
    }

    private void loadCommentData() {
        createCommentIfNotNull(1L, 1L, 1L, null, "test", false);
        createCommentIfNotNull(2L, 1L, 1L, 1L, "testtest", false);
        createCommentIfNotNull(3L, 1L, 1L, 1L, "testtest", false);
        createCommentIfNotNull(4L, 1L, 1L, null, "testtest", true);
    }

    private void loadBookmarkData() {
        createBookmarkIfNotNull(1L, 1L);
        createBookmarkIfNotNull(1L, 2L);
    }

    private void loadIngredientDescriptionData() {
        createIngredientDescriptionIfNotNull(3L, 3L, 1L, "test1");
    }

    private void loadProductData() {
        createProductIfNotNull(1L, "바코드1", 1L, null, null, 1L);
        createProductIfNotNull(2L, "바코드2", 2L, "2023-05-15", null, 2L);
        createProductIfNotNull(3L, "바코드3", 1L, null, "2023-05-15", 3L);
    }

    private void loadUserHasIngredientData() {
        LocalDate now = LocalDate.now();
        Long term = 3L;
        LocalDate target = now.plusDays(term);
        createUserHasIngredientIfNotNull(1L, 1L, "1", now);
        createUserHasIngredientIfNotNull(2L, 1L, "2", now.plusDays(1));
        createUserHasIngredientIfNotNull(3L, 1L, "3", now.plusDays(2));
        createUserHasIngredientIfNotNull(4L, 1L, "4", now.plusDays(3));
        createUserHasIngredientIfNotNull(6L, 1L, "5", now.minusDays(1));
        createUserHasIngredientIfNotNull(7L, 2L, "1", now);
        createUserHasIngredientIfNotNull(8L, 2L, "2", now.minusDays(1));
        createUserHasIngredientIfNotNull(9L, 2L, "3", now.minusDays(2));
        createUserHasIngredientIfNotNull(10L, 2L, "4", now.minusDays(3));
        createUserHasIngredientIfNotNull(11L, 2L, "5", now.plusDays(1));
    }

    private void createUserDataIfNotNull(Long id, String nickname, String username, String password, Boolean isBlocked, String email){
        Optional<UserDetail> byId = userDetailRepository.findById(id);
        if(byId.isPresent()){
            return;
        }
        UserDetail userDetail = UserDetail.builder()
                .nickname(nickname)
                .id(id)
                .username(username)
                .profileImageURI("")
                .isBlocked(isBlocked)
                .marketIngIsAccept(false)
                .build();

        UserLogin userLogin = UserLogin.builder()
                .userDetail(userDetail)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .username(username)
                .provider(UserProvider.NONE)
                .role(RoleType.DEV)
                .build();

        userDetail.setUserLogin(userLogin);
        userDetailRepository.save(userDetail);
    }

    private void createBoardData(Long id, String writerNickname, String title, Long viewCount, BoardSort boardSort) {
        Optional<Board> byId = boardRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }

        UserDetail writer = userDetailRepository.findByNickname(writerNickname).orElseThrow(UserNotFoundException::new);

        Board board = Board.builder()
                .writer(writer)
                .title(title)
                .boardSort(boardSort)
                .commentCount(0L)
                .likeCount(0L)
                .build();

        boardRepository.save(board);
    }


    private void createTestIngredient(String name) {
        Optional<Ingredient> byName = ingredientRepository.findByName(name);

        if(byName.isPresent()) {
            return;
        }

        Ingredient testIngredient = Ingredient.builder()
                .name(name)
                .build();

        ingredientRepository.save(testIngredient);
    }


    private void createRecipeDataIfNotNull(Long id, String thumbnailImgURI, Long boardId, String quantity, String description) {
        Optional<Recipe> byId = recipeRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }

        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        Recipe recipe = Recipe.builder()
                .thumbnailImgURI(thumbnailImgURI)
                .board(board)
                .quantity(RecipeQuantityCategory.valueOf(quantity))
                .description(description)
                .durationTime(RecipeDurationTime.TEN_MINUTES)
                .level(RecipeLevel.보통)
                .sort(RecipeSortCategory.기타)
                .build();

        recipeRepository.save(recipe);
    }



    private void createRecipeRowDataIfNotNull(Long id, String description, Long seqNum, Long recipeId) {
        Optional<RecipeRow> byId = recipeRowRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);

        RecipeRow row = RecipeRow.builder()
                .description(description)
                .seqNum(seqNum)
                .recipe(recipe)
                .build();

        recipeRowRepository.save(row);
    }



    private void createIngredientTrimmingDataIfNotNull(Long id, Long ingredientId, Long boardId) {
        Optional<IngredientTrimming> byId = ingredientTrimmingRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(IngredientNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        IngredientTrimming ingredientTrimming = IngredientTrimming.builder()
                .ingredient(ingredient)
                .board(board)
                .build();

        ingredientTrimmingRepository.save(ingredientTrimming);
    }

    private void createUserBlockIfNotNull(Long userId, Long blockUserId) {
        UserDetail user = userDetailRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UserDetail blockedUser = userDetailRepository.findById(blockUserId).orElseThrow(UserNotFoundException::new);
        Optional<UserBlock> byId = userBlockRepository.findByUserAndBlockedUser(user, blockedUser);

        if(byId.isPresent()){
            return;
        }

        UserBlock userBlock = UserBlock.builder().user(user).blockedUser(blockedUser).build();
        userBlockRepository.save(userBlock);
    }

    private void createBoardLikeIfNotNull(Long boardId, Long userId){
        UserDetail user = userDetailRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        Optional<BoardLike> find = boardLikeRepository.findBoardLikeByBoardAndUser(board,user);
        if(find.isPresent()){
            return;
        }
        else{
            boardLikeRepository.save(BoardLike.builder().user(user).board(board).build());
        }
    }

    private void createUserFollowIfNotNull(Long id, Long userId, Long followingId){
        UserDetail user = userDetailRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UserDetail followingUser = userDetailRepository.findById(followingId).orElseThrow(UserNotFoundException::new);


        Optional<UserFollow> find = userFollowRepository.findUserFollowByUserAndFollowing(user, followingUser);

        if(find.isPresent()){
            return;
        }
        else{
            userFollowRepository.save(UserFollow.builder().user(user).following(followingUser).build());
        }
    }

    private void createCommentIfNotNull(Long commentId, Long boardId, Long userId, Long parentCommentId, String description, Boolean isDeleted){
        Optional<Comment> findComment = commentRepository.findById(commentId);

        if(findComment.isPresent()){
            return;
        }

        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        UserDetail writer= userDetailRepository.findById(userId).orElseThrow(UserNotFoundException::new);


        Comment parentComment = null;
        if(parentCommentId != null){
             parentComment = commentRepository.findById(parentCommentId).orElseThrow(CommentNotFoundException::new);
        }

        Comment comment = Comment.builder().parentComment(parentComment).description(description).writer(writer).board(board).isDeleted(isDeleted).build();

        commentRepository.save(comment);

    }

    private void createBookmarkIfNotNull(Long userId, Long boardId){
        UserDetail user = userDetailRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        BookmarkId bookmarkId = new BookmarkId(user,board);

        Optional<Bookmark> find = bookmarkRepository.findBookmarkByUserAndBoard(user, board);
        if(find.isPresent()){
            return;
        }
        else{
            bookmarkRepository.save(Bookmark.builder().user(user).board(board).build());
        }
    }

    private void createIngredientDescriptionIfNotNull(Long id, Long boardId, Long ingredientId, String description){
        Optional<IngredientDescription> byId = ingredientDescriptionRepository.findById(id);

        if(byId.isPresent()){
            return;
        }else{
            Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(IngredientNotFoundException::new);
            Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
            IngredientDescription build = IngredientDescription.builder().description(description)
                    .board(board)
                    .ingredient(ingredient)
                    .imgURI("")
                    .build();
            ingredientDescriptionRepository.save(build);
        }
    }

    private void createProductIfNotNull(Long barcode, String name, Long ingredientId,String closedDate, String shutdownDate, Long registrationNumber) {


        Optional<Product> byId = productRepository.findById(barcode);

        if(byId.isPresent()){
            return;
        }

        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(IngredientNotFoundException::new);

        Product product = Product.builder()
                .barcode(barcode)
                .productName(name)
                .productShutdownDate(shutdownDate)
                .closedDate(closedDate)
                .productRegistrationNumber(registrationNumber)
                .ingredient(ingredient).build();

        productRepository.save(product);
    }

    private void createNotificationIfNotNull(Long notiId, Long userId, NotificationState state, String title, String body, String navigationId, NotificationSort sort){
        Optional<Notification> findNoti = notificationRepository.findById(notiId);

        if(findNoti.isPresent()){
            return;
        }else {
            UserDetail user = userDetailRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Notification notification = Notification.builder()
                    .sort(sort)
                    .state(state)
                    .user(user)
                    .title(title)
                    .navigationId(navigationId)
                    .body(body)
                    .build();

            notificationRepository.save(notification);
        }

    }

    private void createUserHasIngredientIfNotNull(Long id, Long userId, String name, LocalDate expiredDate) {
        Optional<UserHasIngredient> byId = userHasIngredientRepository.findById(id);

        if(byId.isPresent()){
            return;
        }
        UserDetail userDetail = userDetailRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        UserHasIngredient userHasIngredient = UserHasIngredient.builder()
                .user(userDetail)
                .name(name)
                .expirationDate(expiredDate)
                .build();

        userHasIngredientRepository.save(userHasIngredient);
    }
}
