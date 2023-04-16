package com.solution.recipetalk.domain.user.login.password;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemporaryPassword is a Querydsl query type for TemporaryPassword
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemporaryPassword extends EntityPathBase<TemporaryPassword> {

    private static final long serialVersionUID = -1717839709L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemporaryPassword temporaryPassword1 = new QTemporaryPassword("temporaryPassword1");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath temporaryPassword = createString("temporaryPassword");

    public final com.solution.recipetalk.domain.user.login.entity.QUserLogin userLogin;

    public QTemporaryPassword(String variable) {
        this(TemporaryPassword.class, forVariable(variable), INITS);
    }

    public QTemporaryPassword(Path<? extends TemporaryPassword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemporaryPassword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemporaryPassword(PathMetadata metadata, PathInits inits) {
        this(TemporaryPassword.class, metadata, inits);
    }

    public QTemporaryPassword(Class<? extends TemporaryPassword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userLogin = inits.isInitialized("userLogin") ? new com.solution.recipetalk.domain.user.login.entity.QUserLogin(forProperty("userLogin"), inits.get("userLogin")) : null;
    }

}

