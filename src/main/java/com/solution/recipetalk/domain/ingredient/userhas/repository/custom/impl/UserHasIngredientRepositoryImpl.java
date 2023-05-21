package com.solution.recipetalk.domain.ingredient.userhas.repository.custom.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.solution.recipetalk.domain.ingredient.entity.QIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.entity.QUserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.custom.UserHasIngredientCustomRepository;
import com.solution.recipetalk.dto.ingredient.userhas.UserHasIngredientResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserHasIngredientRepositoryImpl implements UserHasIngredientCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserHasIngredientResponseDTO> findAllUserIngredient(Pageable pageable, String sortElement) {
        QUserHasIngredient userHasIngredient = QUserHasIngredient.userHasIngredient;
        QIngredient ingredient = QIngredient.ingredient;

        OrderSpecifier<?> orderSpecifier = switch (sortElement) {
            case "alphabet_asc" -> new OrderSpecifier<>(Order.ASC, userHasIngredient.ingredient.name);
            case "alphabet_desc" -> new OrderSpecifier<>(Order.DESC, userHasIngredient.ingredient.name);
            case "expiry_date_immi" -> new OrderSpecifier<>(Order.ASC, userHasIngredient.expirationDate);
            case "expiry_date_spare" -> new OrderSpecifier<>(Order.DESC, userHasIngredient.expirationDate);
            case "new" -> new OrderSpecifier<>(Order.ASC, userHasIngredient.createdDate);
            case "old" -> new OrderSpecifier<>(Order.DESC, userHasIngredient.createdDate);
            default -> null;
        };

        JPAQuery<UserHasIngredientResponseDTO> query = queryFactory
                .select(Projections.bean(UserHasIngredientResponseDTO.class, userHasIngredient.name.as("ingredientName"), userHasIngredient.state, userHasIngredient.quantity, userHasIngredient.expirationDate, userHasIngredient.ingredient.id.as("ingredientId"), userHasIngredient.id.as("userHasIngredientId")))
                .from(userHasIngredient)
                .join(userHasIngredient.ingredient, ingredient).on(userHasIngredient.ingredient.eq(ingredient))
                .orderBy(orderSpecifier);

        JPAQuery<UserHasIngredient> countQuery = queryFactory.selectFrom(userHasIngredient);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, () -> countQuery.fetch().size());
    }
}
