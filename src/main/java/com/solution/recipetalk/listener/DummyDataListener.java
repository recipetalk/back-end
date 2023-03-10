package com.solution.recipetalk.listener;

import com.solution.recipetalk.domain.bill.repository.BillRepository;
import com.solution.recipetalk.domain.board.entity.Board;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.ingredient.description.repository.IngredientDescriptionRepository;
import com.solution.recipetalk.domain.ingredient.entity.Ingredient;
import com.solution.recipetalk.domain.ingredient.entity.IngredientSort;
import com.solution.recipetalk.domain.ingredient.entity.IngredientState;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.recipe.entity.Recipe;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.entity.RecipeRow;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.domain.report.repository.ReportRepository;
import com.solution.recipetalk.domain.user.block.entity.UserBlock;
import com.solution.recipetalk.domain.user.block.id.UserBlockId;
import com.solution.recipetalk.domain.user.block.repository.UserBlockRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.entity.UserProvider;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import com.solution.recipetalk.exception.board.BoardNotFoundException;
import com.solution.recipetalk.exception.ingredient.IngredientNotFoundException;
import com.solution.recipetalk.exception.recipe.RecipeNotFoundException;
import com.solution.recipetalk.exception.user.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Component
@AllArgsConstructor
@Transactional
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserBlockRepository userBlockRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUserData();
        loadTestUserData();
        loadBoardData();
        loadIngredientData();
        loadRecipeData();
        loadRecipeRowData();
        loadIngredientTrimmingData();
        loadUserBlockData();
    }

    private void loadUserData() {
        //테스트를 위해 id : 2L로 시작함.
        createUserDataIfNotNull(1L, "hyunkim", "khj745700", "testtest", "01031798788");
    }

    private void loadTestUserData() {
        createUserDataIfNotNull(2L, "test", "test", "test", "01012341234");
        createUserDataIfNotNull(3L, "test1", "test1", "test", "01012344321");
    }

    private void loadBoardData() {
        createBoardData(1L, "test", "test board", 0L);
        createBoardData(2L, "test1", "test board2", 0L);
    }

    private void loadIngredientData() {
        createTestIngredient("ingredient1", IngredientSort.마늘, IngredientState.다짐, 10);
        createTestIngredient("ingredient2", IngredientSort.마늘, IngredientState.다짐, 6);
    }

    private void loadRecipeData() {
        createRecipeDataIfNotNull(1L, "", 1L, 1L, "sample");
    }

    private void loadRecipeRowData() {
        createRecipeRowDataIfNotNull(1L, "kind of food", 2L, 1L);
        createRecipeRowDataIfNotNull(2L, "kind of recipe", 0L, 1L);
    }

    private void loadIngredientTrimmingData() {
        createIngredientTrimmingDataIfNotNull(1L, 1L, 2L);
    }

    private void loadUserBlockData() {
        createUserBlockIfNotNull(1L,2L);
        createUserBlockIfNotNull(1L,3L);
    }



    private void createUserDataIfNotNull(Long id, String nickname, String username, String password, String phoneNum){
        Optional<UserDetail> byId = userDetailRepository.findById(id);
        if(byId.isPresent()){
            return;
        }
        UserDetail userDetail = UserDetail.builder()
                .nickname(nickname)
                .id(id)
                .phoneNum(phoneNum)
                .username(username)
                .profileImageURI("")
                .build();

        UserLogin userLogin = UserLogin.builder()
                .userDetail(userDetail)
                .password(bCryptPasswordEncoder.encode(password))
                .username(username)
                .provider(UserProvider.NONE)
                .role(RoleType.DEV)
                .build();

        userDetail.setUserLogin(userLogin);
        userDetailRepository.save(userDetail);
    }

    private void createBoardData(Long id, String writerNickname, String title, Long viewCount) {
        Optional<Board> byId = boardRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }

        UserDetail writer = userDetailRepository.findByNickname(writerNickname).orElseThrow(UserNotFoundException::new);

        Board board = Board.builder()
                .writer(writer)
                .title(title)
                .view_count(viewCount)
                .build();

        boardRepository.save(board);
    }


    private void createTestIngredient(String name, IngredientSort sort, IngredientState state, Integer calorie) {
        Optional<Ingredient> byName = ingredientRepository.findByName(name);

        if(byName.isPresent()) {
            return;
        }

        Ingredient testIngredient = Ingredient.builder()
                .name(name)
                .sort(sort)
                .state(state)
                .calorie(calorie)
                .build();

        ingredientRepository.save(testIngredient);
    }


    private void createRecipeDataIfNotNull(Long id, String thumbnailImgURI, Long boardId, Long quantity, String description) {
        Optional<Recipe> byId = recipeRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }

        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        Recipe recipe = Recipe.builder()
                .thumbnailImgURI(thumbnailImgURI)
                .board(board)
                .quantity(quantity)
                .description(description)
                .build();

        recipeRepository.save(recipe);
    }



    private void createRecipeRowDataIfNotNull(Long id, String description, Long timer, Long recipeId) {
        Optional<RecipeRow> byId = recipeRowRepository.findById(id);
        if(byId.isPresent()) {
            return;
        }

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(RecipeNotFoundException::new);

        RecipeRow row = RecipeRow.builder()
                .description(description)
                .timer(timer)
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
        UserBlockId userBlockId = new UserBlockId(user, blockedUser);
        Optional<UserBlock> byId = userBlockRepository.findById(userBlockId);

        if(byId.isPresent()){
            return;
        }

        UserBlock userBlock = UserBlock.builder().user(user).blockedUser(blockedUser).build();
        userBlockRepository.save(userBlock);
    }
}
