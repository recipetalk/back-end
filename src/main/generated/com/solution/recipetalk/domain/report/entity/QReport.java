package com.solution.recipetalk.domain.report.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = 91010147L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final com.solution.recipetalk.domain.common.QAuditingEntity _super = new com.solution.recipetalk.domain.common.QAuditingEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final com.solution.recipetalk.domain.user.entity.QUserDetail reportee;

    public final com.solution.recipetalk.domain.user.entity.QUserDetail reporter;

    public final EnumPath<ReportState> reportState = createEnum("reportState", ReportState.class);

    public final StringPath screenshotURI = createString("screenshotURI");

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReport(PathMetadata metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reportee = inits.isInitialized("reportee") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("reportee"), inits.get("reportee")) : null;
        this.reporter = inits.isInitialized("reporter") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("reporter"), inits.get("reporter")) : null;
    }

}

