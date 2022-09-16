package com.mohaeng.meeting.domain.applyform;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplyFormField is a Querydsl query type for ApplyFormField
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplyFormField extends EntityPathBase<ApplyFormField> {

    private static final long serialVersionUID = -552507762L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplyFormField applyFormField = new QApplyFormField("applyFormField");

    public final com.mohaeng.meeting.configuration.jpa.QBaseEntity _super = new com.mohaeng.meeting.configuration.jpa.QBaseEntity(this);

    public final QApplyForm applyForm;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public QApplyFormField(String variable) {
        this(ApplyFormField.class, forVariable(variable), INITS);
    }

    public QApplyFormField(Path<? extends ApplyFormField> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplyFormField(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplyFormField(PathMetadata metadata, PathInits inits) {
        this(ApplyFormField.class, metadata, inits);
    }

    public QApplyFormField(Class<? extends ApplyFormField> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.applyForm = inits.isInitialized("applyForm") ? new QApplyForm(forProperty("applyForm")) : null;
    }

}

