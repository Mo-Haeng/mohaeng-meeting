package com.mohaeng.meeting.domain.writtenapplyform;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWrittenApplyFormField is a Querydsl query type for WrittenApplyFormField
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWrittenApplyFormField extends EntityPathBase<WrittenApplyFormField> {

    private static final long serialVersionUID = -863725098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWrittenApplyFormField writtenApplyFormField = new QWrittenApplyFormField("writtenApplyFormField");

    public final com.mohaeng.meeting.configuration.jpa.QBaseEntity _super = new com.mohaeng.meeting.configuration.jpa.QBaseEntity(this);

    public final NumberPath<Long> applyFormFieldId = createNumber("applyFormFieldId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QWrittenApplyForm writtenApplyForm;

    public QWrittenApplyFormField(String variable) {
        this(WrittenApplyFormField.class, forVariable(variable), INITS);
    }

    public QWrittenApplyFormField(Path<? extends WrittenApplyFormField> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWrittenApplyFormField(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWrittenApplyFormField(PathMetadata metadata, PathInits inits) {
        this(WrittenApplyFormField.class, metadata, inits);
    }

    public QWrittenApplyFormField(Class<? extends WrittenApplyFormField> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writtenApplyForm = inits.isInitialized("writtenApplyForm") ? new QWrittenApplyForm(forProperty("writtenApplyForm")) : null;
    }

}

