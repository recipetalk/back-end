package com.solution.recipetalk.domain.user.login.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLogin is a Querydsl query type for UserLogin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLogin extends EntityPathBase<UserLogin> {

    private static final long serialVersionUID = -306980931L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLogin userLogin = new QUserLogin("userLogin");

    public final com.solution.recipetalk.domain.common.QSoftDeleteEntity _super = new com.solution.recipetalk.domain.common.QSoftDeleteEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath password = createString("password");

    public final EnumPath<UserProvider> provider = createEnum("provider", UserProvider.class);

    public final EnumPath<RoleType> role = createEnum("role", RoleType.class);

    public final com.solution.recipetalk.domain.user.entity.QUserDetail userDetail;

    public final StringPath username = createString("username");

    public QUserLogin(String variable) {
        this(UserLogin.class, forVariable(variable), INITS);
    }

    public QUserLogin(Path<? extends UserLogin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLogin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLogin(PathMetadata metadata, PathInits inits) {
        this(UserLogin.class, metadata, inits);
    }

    public QUserLogin(Class<? extends UserLogin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userDetail = inits.isInitialized("userDetail") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("userDetail"), inits.get("userDetail")) : null;
    }

}

