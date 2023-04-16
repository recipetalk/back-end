package com.solution.recipetalk.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSoftDeleteEntity is a Querydsl query type for SoftDeleteEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QSoftDeleteEntity extends EntityPathBase<SoftDeleteEntity> {

    private static final long serialVersionUID = 924949981L;

    public static final QSoftDeleteEntity softDeleteEntity = new QSoftDeleteEntity("softDeleteEntity");

    public final QAuditingEntity _super = new QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QSoftDeleteEntity(String variable) {
        super(SoftDeleteEntity.class, forVariable(variable));
    }

    public QSoftDeleteEntity(Path<? extends SoftDeleteEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSoftDeleteEntity(PathMetadata metadata) {
        super(SoftDeleteEntity.class, metadata);
    }

}

