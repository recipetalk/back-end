package com.solution.recipetalk.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1524366433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.solution.recipetalk.domain.common.QSoftDeleteEntity _super = new com.solution.recipetalk.domain.common.QSoftDeleteEntity(this);

    public final ListPath<com.solution.recipetalk.domain.board.like.entity.BoardLike, com.solution.recipetalk.domain.board.like.entity.QBoardLike> boardLikes = this.<com.solution.recipetalk.domain.board.like.entity.BoardLike, com.solution.recipetalk.domain.board.like.entity.QBoardLike>createList("boardLikes", com.solution.recipetalk.domain.board.like.entity.BoardLike.class, com.solution.recipetalk.domain.board.like.entity.QBoardLike.class, PathInits.DIRECT2);

    public final ListPath<com.solution.recipetalk.domain.comment.entity.Comment, com.solution.recipetalk.domain.comment.entity.QComment> comments = this.<com.solution.recipetalk.domain.comment.entity.Comment, com.solution.recipetalk.domain.comment.entity.QComment>createList("comments", com.solution.recipetalk.domain.comment.entity.Comment.class, com.solution.recipetalk.domain.comment.entity.QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final NumberPath<Long> view_count = createNumber("view_count", Long.class);

    public final com.solution.recipetalk.domain.user.entity.QUserDetail writer;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writer = inits.isInitialized("writer") ? new com.solution.recipetalk.domain.user.entity.QUserDetail(forProperty("writer"), inits.get("writer")) : null;
    }

}

