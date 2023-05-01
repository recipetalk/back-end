package com.solution.recipetalk.domain.recipe.query;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.solution.recipetalk.domain.board.bookmark.entity.QBookmark;
import com.solution.recipetalk.domain.board.entity.QBoard;
import com.solution.recipetalk.domain.board.like.entity.QBoardLike;
import com.solution.recipetalk.domain.recipe.entity.QRecipe;
import com.solution.recipetalk.domain.recipe.repository.RecipeQueryDslRepository;
import com.solution.recipetalk.domain.user.block.entity.QUserBlock;
import com.solution.recipetalk.domain.user.follow.entity.QUserFollow;
import com.solution.recipetalk.dto.recipe.RecipeListReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class RecipeRepositoryImpl implements RecipeQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private JPAQuery<Tuple> createCommonSql(Long userId) {
        QRecipe qRecipe = QRecipe.recipe;
        QBoard qBoard = QBoard.board;
        QUserBlock qUserBlock = QUserBlock.userBlock;
        QBookmark qBookmark = QBookmark.bookmark;
        QBoardLike qBoardLike = QBoardLike.boardLike;
        BooleanExpression notInBlockedBoards = qBoard.writer.id.notIn(
                JPAExpressions.select(qUserBlock.blockedUser.id)
                        .from(qUserBlock)
                        .where(qUserBlock.user.id.eq(userId)));
        BooleanExpression isBookMarked = qBookmark.user.id.isNotNull();
        BooleanExpression isLiked = qBoardLike.user.id.isNotNull();

        return jpaQueryFactory.select(qRecipe, isBookMarked.as("isBookmarked"), isLiked.as("isLiked"))
                .from(qRecipe)
                .join(qRecipe.board, qBoard)
                .on(notInBlockedBoards);

    }

    private JPAQuery<Tuple> getBookmarkedAndBoardLike(JPAQuery<Tuple> queryBuilder, Long userId){
        QBoard qBoard = QBoard.board;
        QBookmark qBookmark = QBookmark.bookmark;
        QBoardLike qBoardLike = QBoardLike.boardLike;

        return queryBuilder.leftJoin(qBookmark)
                .on(qBookmark.user.id.eq(userId).and(qBookmark.board.eq(qBoard)))
                .leftJoin(qBoardLike)
                .on(qBoardLike.user.id.eq(userId).and(qBoardLike.board.eq(qBoard)));
    }


    @Override
    public List<RecipeForList> findRecipeList(RecipeListReqDTO dto, Long userId){
        QRecipe qRecipe = QRecipe.recipe;
        QBoard qBoard = QBoard.board;
        QUserFollow qUserFollow = QUserFollow.userFollow;

        JPAQuery<Tuple> queryBuilder = createCommonSql(userId);

        // 타이틀이 존재하는 경우
        if (dto.getTitle() != null) {
            BooleanExpression boardTitleContains = qBoard.title.like("%" + dto.getTitle() + "%");
            queryBuilder = queryBuilder.on(boardTitleContains);
        }


        // 카테고리 2개 존재하는 경우
        if (dto.getSituationCategory() != null && dto.getSortCategory() != null){
            BooleanExpression sortCategoryEquals = qRecipe.sort.eq(dto.getSortCategory());
            BooleanExpression situationCategoryEquals = qRecipe.situation.eq(dto.getSituationCategory());
            queryBuilder = queryBuilder.where(sortCategoryEquals.and(situationCategoryEquals));
        }
        // sort 만 존재하는 경우
        else if (dto.getSortCategory() != null){
            queryBuilder = queryBuilder.where(qRecipe.sort.eq(dto.getSortCategory()));
        }
        // situation 만 존재하는 경우
        else if (dto.getSituationCategory() != null){
            queryBuilder = queryBuilder.where(qRecipe.situation.eq(dto.getSituationCategory()));
        }


        switch (dto.getSortType()) {
            case NEW -> {
                queryBuilder = queryBuilder.orderBy(QBoard.board.createdDate.desc());
            }
            case POPULAR -> {
                queryBuilder = queryBuilder.orderBy(QBoard.board.likeCount.desc());
            }
            case FOLLOW -> {
                queryBuilder = queryBuilder.leftJoin(qUserFollow)
                        .on(qUserFollow.user.id.eq(userId).and(qUserFollow.following.id.eq(qBoard.writer.id)))
                        .orderBy(qBoard.createdDate.desc());
            }
        }

        queryBuilder = getBookmarkedAndBoardLike(queryBuilder, userId);

        queryBuilder = queryBuilder.offset(dto.getOffset())
                                .limit(dto.getLimit());
        return RecipeForList.toRecipeForList(queryBuilder.fetch());
    }

}
