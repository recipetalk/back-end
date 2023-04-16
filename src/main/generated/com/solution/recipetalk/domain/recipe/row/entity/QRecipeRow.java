package com.solution.recipetalk.domain.recipe.row.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeRow is a Querydsl query type for RecipeRow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeRow extends EntityPathBase<RecipeRow> {

    private static final long serialVersionUID = 1350475607L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeRow recipeRow = new QRecipeRow("recipeRow");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.solution.recipetalk.domain.recipe.entity.QRecipe recipe;

    public final NumberPath<Long> timer = createNumber("timer", Long.class);

    public QRecipeRow(String variable) {
        this(RecipeRow.class, forVariable(variable), INITS);
    }

    public QRecipeRow(Path<? extends RecipeRow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeRow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeRow(PathMetadata metadata, PathInits inits) {
        this(RecipeRow.class, metadata, inits);
    }

    public QRecipeRow(Class<? extends RecipeRow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new com.solution.recipetalk.domain.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
    }

}

