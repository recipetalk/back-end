package com.solution.recipetalk.domain.ingredient.trimming.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.solution.recipetalk.domain.board.entity.QBoard;
import com.solution.recipetalk.domain.common.SortType;
import com.solution.recipetalk.domain.ingredient.trimming.repository.IngredientTrimmingQueryDslRepository;
import com.solution.recipetalk.domain.ingredient.trimming.entity.IngredientTrimming;
import com.solution.recipetalk.domain.ingredient.trimming.entity.QIngredientTrimming;
import com.solution.recipetalk.domain.user.entity.QUserDetail;
import com.solution.recipetalk.dto.ingredient.trimming.IngredientTrimmingByUserReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class IngredientTrimmingRepositoryImpl implements IngredientTrimmingQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<IngredientTrimming> findIngredientTrimmingList(IngredientTrimmingByUserReqDTO dto, Long userId){
        QIngredientTrimming qIngredientTrimming = QIngredientTrimming.ingredientTrimming;
        QBoard qBoard = QBoard.board;
        QUserDetail qUserDetail = QUserDetail.userDetail;

        JPAQuery<IngredientTrimming> queryBuilder = jpaQueryFactory.selectFrom(qIngredientTrimming)
                .join(qBoard)
                .on(qBoard.id.eq(qIngredientTrimming.board.id)
                        .and(qBoard.writer.id.eq(userId))
                        .and(qBoard.isDeleted.eq(false)))
                .join(qUserDetail)
                .on(qUserDetail.id.eq(qBoard.writer.id)
                        .and(qUserDetail.isBlocked.eq(false))
                        .and(qUserDetail.isDeleted.eq(false)));

        queryBuilder = getSqlBySortType(queryBuilder, dto.getSortType());
        queryBuilder = getSQlByOrderBy(queryBuilder, dto.getLimit(), dto.getOffset());

        return queryBuilder.fetch();
    }

    private JPAQuery<IngredientTrimming> getSqlBySortType(JPAQuery<IngredientTrimming> queryBuilder, SortType sortType){
        switch (sortType) {
            case NEW -> {
                queryBuilder = queryBuilder.orderBy(QBoard.board.createdDate.desc());
            }
            case POPULAR -> {
                queryBuilder = queryBuilder.orderBy(QBoard.board.likeCount.desc());
            }
        }
        return queryBuilder;
    }

    private JPAQuery<IngredientTrimming> getSQlByOrderBy(JPAQuery<IngredientTrimming> queryBuilder, long limit, long offset){
        return queryBuilder.offset(offset)
                .limit(limit);
    }

}
