package com.solution.recipetalk.domain.user.block.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBlock is a Querydsl query type for UserBlock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserBlock extends EntityPathBase<UserBlock> {

    private static final long serialVersionUID = -1552137155L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBlock userBlock = new QUserBlock("userBlock");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    public final com.solution.recipetalk.domain.user.entity.QUserDetail blockedUser;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.solution.recipetalk.domain.user.entity.QUserDetail user;

    public QUserBlock(String variable) {
        this(UserBlock.class, forVariable(variable), INITS);
    }

    public QUserBlock(Path<? extends UserBlock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBlock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBlock(PathMetadata metadata, PathInits inits) {
        this(UserBlock.class, metadata, inits);
    }

    public QUserBlock(Class<? extends UserBlock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockedUser = inits.isInitialized("blockedUser") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("blockedUser"), inits.get("blockedUser")) : null;
        this.user = inits.isInitialized("user") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("user"), inits.get("user")) : null;
    }

}

