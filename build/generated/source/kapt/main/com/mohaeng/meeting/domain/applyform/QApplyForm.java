package com.mohaeng.meeting.domain.applyform;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplyForm is a Querydsl query type for ApplyForm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplyForm extends EntityPathBase<ApplyForm> {

    private static final long serialVersionUID = -612070964L;

    public static final QApplyForm applyForm = new QApplyForm("applyForm");

    public final com.mohaeng.meeting.configuration.jpa.QBaseEntity _super = new com.mohaeng.meeting.configuration.jpa.QBaseEntity(this);

    public final ListPath<ApplyFormField, QApplyFormField> applyFormFields = this.<ApplyFormField, QApplyFormField>createList("applyFormFields", ApplyFormField.class, QApplyFormField.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isCurrentUsed = createBoolean("isCurrentUsed");

    public final NumberPath<Long> meetingId = createNumber("meetingId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public QApplyForm(String variable) {
        super(ApplyForm.class, forVariable(variable));
    }

    public QApplyForm(Path<? extends ApplyForm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplyForm(PathMetadata metadata) {
        super(ApplyForm.class, metadata);
    }

}

