package com.solution.recipetalk.domain.recipe.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipe is a Querydsl query type for Recipe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipe extends EntityPathBase<Recipe> {

    private static final long serialVersionUID = 520359831L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipe recipe = new QRecipe("recipe");

    public final com.solution.recipetalk.domain.common.QSoftDeleteEntity _super = new com.solution.recipetalk.domain.common.QSoftDeleteEntity(this);

    public final com.solution.recipetalk.domain.board.entity.QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public final ListPath<com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient, com.solution.recipetalk.domain.recipe.ingredient.entity.QRecipeIngredient> recipeIngredients = this.<com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient, com.solution.recipetalk.domain.recipe.ingredient.entity.QRecipeIngredient>createList("recipeIngredients", com.solution.recipetalk.domain.recipe.ingredient.entity.RecipeIngredient.class, com.solution.recipetalk.domain.recipe.ingredient.entity.QRecipeIngredient.class, PathInits.DIRECT2);

    public final ListPath<com.solution.recipetalk.domain.recipe.row.entity.RecipeRow, com.solution.recipetalk.domain.recipe.row.entity.QRecipeRow> recipeRows = this.<com.solution.recipetalk.domain.recipe.row.entity.RecipeRow, com.solution.recipetalk.domain.recipe.row.entity.QRecipeRow>createList("recipeRows", com.solution.recipetalk.domain.recipe.row.entity.RecipeRow.class, com.solution.recipetalk.domain.recipe.row.entity.QRecipeRow.class, PathInits.DIRECT2);

    public final StringPath thumbnailImgURI = createString("thumbnailImgURI");

    public QRecipe(String variable) {
        this(Recipe.class, forVariable(variable), INITS);
    }

    public QRecipe(Path<? extends Recipe> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipe(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipe(PathMetadata metadata, PathInits inits) {
        this(Recipe.class, metadata, inits);
    }

    public QRecipe(Class<? extends Recipe> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.solution.recipetalk.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

