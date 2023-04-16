package com.solution.recipetalk.domain.ingredient.description.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredientDescription is a Querydsl query type for IngredientDescription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientDescription extends EntityPathBase<IngredientDescription> {

    private static final long serialVersionUID = 1711866897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngredientDescription ingredientDescription = new QIngredientDescription("ingredientDescription");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    public final com.solution.recipetalk.domain.board.entity.QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgURI = createString("imgURI");

    public final com.solution.recipetalk.domain.ingredient.entity.QIngredient ingredient;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QIngredientDescription(String variable) {
        this(IngredientDescription.class, forVariable(variable), INITS);
    }

    public QIngredientDescription(Path<? extends IngredientDescription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngredientDescription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngredientDescription(PathMetadata metadata, PathInits inits) {
        this(IngredientDescription.class, metadata, inits);
    }

    public QIngredientDescription(Class<? extends IngredientDescription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.solution.recipetalk.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.ingredient = inits.isInitialized("ingredient") ? new com.solution.recipetalk.domain.ingredient.entity.QIngredient(forProperty("ingredient")) : null;
    }

}

