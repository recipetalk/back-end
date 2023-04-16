package com.solution.recipetalk.domain.ingredient.userhas.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserHasIngredient is a Querydsl query type for UserHasIngredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserHasIngredient extends EntityPathBase<UserHasIngredient> {

    private static final long serialVersionUID = -1740686445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserHasIngredient userHasIngredient = new QUserHasIngredient("userHasIngredient");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DatePath<java.time.LocalDate> expirationDate = createDate("expirationDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.solution.recipetalk.domain.ingredient.entity.QIngredient ingredient;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath quantity = createString("quantity");

    public final EnumPath<IngredientState> state = createEnum("state", IngredientState.class);

    public final com.solution.recipetalk.domain.user.entity.QUserDetail user;

    public QUserHasIngredient(String variable) {
        this(UserHasIngredient.class, forVariable(variable), INITS);
    }

    public QUserHasIngredient(Path<? extends UserHasIngredient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserHasIngredient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserHasIngredient(PathMetadata metadata, PathInits inits) {
        this(UserHasIngredient.class, metadata, inits);
    }

    public QUserHasIngredient(Class<? extends UserHasIngredient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new com.solution.recipetalk.domain.ingredient.entity.QIngredient(forProperty("ingredient")) : null;
        this.user = inits.isInitialized("user") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("user"), inits.get("user")) : null;
    }

}

