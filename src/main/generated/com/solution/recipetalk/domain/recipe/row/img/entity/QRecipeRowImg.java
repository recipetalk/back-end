package com.solution.recipetalk.domain.recipe.row.img.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeRowImg is a Querydsl query type for RecipeRowImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeRowImg extends EntityPathBase<RecipeRowImg> {

    private static final long serialVersionUID = -2067905215L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeRowImg recipeRowImg = new QRecipeRowImg("recipeRowImg");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final com.solution.recipetalk.domain.image.entity.QImage image;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.solution.recipetalk.domain.recipe.row.entity.QRecipeRow recipeRow;

    public QRecipeRowImg(String variable) {
        this(RecipeRowImg.class, forVariable(variable), INITS);
    }

    public QRecipeRowImg(Path<? extends RecipeRowImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeRowImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeRowImg(PathMetadata metadata, PathInits inits) {
        this(RecipeRowImg.class, metadata, inits);
    }

    public QRecipeRowImg(Class<? extends RecipeRowImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new com.solution.recipetalk.domain.image.entity.QImage(forProperty("image")) : null;
        this.recipeRow = inits.isInitialized("recipeRow") ? new com.solution.recipetalk.domain.recipe.row.entity.QRecipeRow(forProperty("recipeRow"), inits.get("recipeRow")) : null;
    }

}

