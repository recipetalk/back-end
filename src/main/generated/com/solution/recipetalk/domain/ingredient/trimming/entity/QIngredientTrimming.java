package com.solution.recipetalk.domain.ingredient.trimming.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredientTrimming is a Querydsl query type for IngredientTrimming
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientTrimming extends EntityPathBase<IngredientTrimming> {

    private static final long serialVersionUID = 1563900379L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngredientTrimming ingredientTrimming = new QIngredientTrimming("ingredientTrimming");

    public final com.solution.recipetalk.domain.common.QSoftDeleteEntity _super = new com.solution.recipetalk.domain.common.QSoftDeleteEntity(this);

    public final com.solution.recipetalk.domain.board.entity.QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.solution.recipetalk.domain.ingredient.entity.QIngredient ingredient;

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath thumbnailUri = createString("thumbnailUri");

    public QIngredientTrimming(String variable) {
        this(IngredientTrimming.class, forVariable(variable), INITS);
    }

    public QIngredientTrimming(Path<? extends IngredientTrimming> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngredientTrimming(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngredientTrimming(PathMetadata metadata, PathInits inits) {
        this(IngredientTrimming.class, metadata, inits);
    }

    public QIngredientTrimming(Class<? extends IngredientTrimming> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.solution.recipetalk.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.ingredient = inits.isInitialized("ingredient") ? new com.solution.recipetalk.domain.ingredient.entity.QIngredient(forProperty("ingredient")) : null;
    }

}

