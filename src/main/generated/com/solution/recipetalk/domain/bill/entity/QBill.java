package com.solution.recipetalk.domain.bill.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBill is a Querydsl query type for Bill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBill extends EntityPathBase<Bill> {

    private static final long serialVersionUID = 1675783369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBill bill = new QBill("bill");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageURI = createString("imageURI");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.solution.recipetalk.domain.user.entity.QUserDetail user;

    public QBill(String variable) {
        this(Bill.class, forVariable(variable), INITS);
    }

    public QBill(Path<? extends Bill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBill(PathMetadata metadata, PathInits inits) {
        this(Bill.class, metadata, inits);
    }

    public QBill(Class<? extends Bill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("user"), inits.get("user")) : null;
    }

}

