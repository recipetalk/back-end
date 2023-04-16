package com.solution.recipetalk.domain.fcm.entity.temp.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTempFcmToken is a Querydsl query type for TempFcmToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTempFcmToken extends EntityPathBase<TempFcmToken> {

    private static final long serialVersionUID = -1142918371L;

    public static final QTempFcmToken tempFcmToken = new QTempFcmToken("tempFcmToken");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath fcmToken = createString("fcmToken");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QTempFcmToken(String variable) {
        super(TempFcmToken.class, forVariable(variable));
    }

    public QTempFcmToken(Path<? extends TempFcmToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTempFcmToken(PathMetadata metadata) {
        super(TempFcmToken.class, metadata);
    }

}

