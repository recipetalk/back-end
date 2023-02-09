package com.solution.recipetalk.listener;

import com.solution.recipetalk.domain.bill.repository.BillRepository;
import com.solution.recipetalk.domain.board.like.repository.BoardLikeRepository;
import com.solution.recipetalk.domain.board.repository.BoardRepository;
import com.solution.recipetalk.domain.comment.repository.CommentRepository;
import com.solution.recipetalk.domain.image.repository.ImageRepository;
import com.solution.recipetalk.domain.ingredient.description.repository.IngredientDescriptionRepository;
import com.solution.recipetalk.domain.ingredient.repository.IngredientRepository;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingRepository;
import com.solution.recipetalk.domain.ingredient.trimming.row.repository.IngredientTrimmingRowRepository;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.domain.report.repository.ReportRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import com.solution.recipetalk.domain.user.login.entity.RoleType;
import com.solution.recipetalk.domain.user.login.entity.UserLogin;
import com.solution.recipetalk.domain.user.login.entity.UserProvider;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    private final RecipeRepository repository;
    private final RecipeRowRepository recipeRowRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ReportRepository reportRepository;
    private final UserLoginRepository userLoginRepository;
    private final UserDetailRepository userDetailRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUserData();
    }

    private void loadUserData() {
        //테스트를 위해 id : 2L로 시작함.
        createUserDataIfNotNull(2L, "hyunkim", "khj745700", "testtest", "01031798788");
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
}
