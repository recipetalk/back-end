package com.solution.recipetalk.domain.user.phone.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhoneAuthentication is a Querydsl query type for PhoneAuthentication
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneAuthentication extends EntityPathBase<PhoneAuthentication> {

    private static final long serialVersionUID = 1510284544L;

    public static final QPhoneAuthentication phoneAuthentication = new QPhoneAuthentication("phoneAuthentication");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    public final StringPath authNum = createString("authNum");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath isAuthentication = createBoolean("isAuthentication");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath phoneNum = createString("phoneNum");

    public QPhoneAuthentication(String variable) {
        super(PhoneAuthentication.class, forVariable(variable));
    }

    public QPhoneAuthentication(Path<? extends PhoneAuthentication> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhoneAuthentication(PathMetadata metadata) {
        super(PhoneAuthentication.class, metadata);
    }

}

