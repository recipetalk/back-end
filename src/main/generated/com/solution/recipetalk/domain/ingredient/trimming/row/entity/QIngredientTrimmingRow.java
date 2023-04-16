package com.solution.recipetalk.domain.ingredient.trimming.row.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredientTrimmingRow is a Querydsl query type for IngredientTrimmingRow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientTrimmingRow extends EntityPathBase<IngredientTrimmingRow> {

    private static final long serialVersionUID = -1281718893L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngredientTrimmingRow ingredientTrimmingRow = new QIngredientTrimmingRow("ingredientTrimmingRow");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgURI = createString("imgURI");

    public final com.solution.recipetalk.domain.ingredient.trimming.entity.QIngredientTrimming ingredientTrimming;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> trimmingSeq = createNumber("trimmingSeq", Long.class);

    public final NumberPath<Long> trimmingSubSeq = createNumber("trimmingSubSeq", Long.class);

    public QIngredientTrimmingRow(String variable) {
        this(IngredientTrimmingRow.class, forVariable(variable), INITS);
    }

    public QIngredientTrimmingRow(Path<? extends IngredientTrimmingRow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngredientTrimmingRow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngredientTrimmingRow(PathMetadata metadata, PathInits inits) {
        this(IngredientTrimmingRow.class, metadata, inits);
    }

    public QIngredientTrimmingRow(Class<? extends IngredientTrimmingRow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredientTrimming = inits.isInitialized("ingredientTrimming") ? new com.solution.recipetalk.domain.ingredient.trimming.entity.QIngredientTrimming(forProperty("ingredientTrimming"), inits.get("ingredientTrimming")) : null;
    }

}

