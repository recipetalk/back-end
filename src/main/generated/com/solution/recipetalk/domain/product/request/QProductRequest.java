package com.solution.recipetalk.domain.product.request;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductRequest is a Querydsl query type for ProductRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductRequest extends EntityPathBase<ProductRequest> {

    private static final long serialVersionUID = -980350916L;

    public static final QProductRequest productRequest = new QProductRequest("productRequest");

    public final NumberPath<Long> barcode = createNumber("barcode", Long.class);

    public final StringPath productName = createString("productName");

    public final EnumPath<ProductRequestState> state = createEnum("state", ProductRequestState.class);

    public QProductRequest(String variable) {
        super(ProductRequest.class, forVariable(variable));
    }

    public QProductRequest(Path<? extends ProductRequest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductRequest(PathMetadata metadata) {
        super(ProductRequest.class, metadata);
    }

}

