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
import com.solution.recipetalk.domain.recipe.ingredient.group.repository.RecipeIngredientGroupRepository;
import com.solution.recipetalk.domain.recipe.ingredient.repository.RecipeIngredientRepository;
import com.solution.recipetalk.domain.recipe.repository.RecipeRepository;
import com.solution.recipetalk.domain.recipe.row.repository.RecipeRowRepository;
import com.solution.recipetalk.domain.report.repository.ReportRepository;
import com.solution.recipetalk.domain.user.login.repository.UserLoginRepository;
import com.solution.recipetalk.domain.user.repository.UserDetailRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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
    private final RecipeIngredientGroupRepository recipeIngredientGroupRepository;
    private final ReportRepository reportRepository;
    private final UserLoginRepository userLoginRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
