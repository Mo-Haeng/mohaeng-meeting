package com.mohaeng.meeting.domain.writtenapplyform;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWrittenApplyForm is a Querydsl query type for WrittenApplyForm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWrittenApplyForm extends EntityPathBase<WrittenApplyForm> {

    private static final long serialVersionUID = 1235249540L;

    public static final QWrittenApplyForm writtenApplyForm = new QWrittenApplyForm("writtenApplyForm");

    public final com.mohaeng.meeting.configuration.jpa.QBaseEntity _super = new com.mohaeng.meeting.configuration.jpa.QBaseEntity(this);

    public final NumberPath<Long> applyFormId = createNumber("applyFormId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<WrittenApplyFormField, QWrittenApplyFormField> writtenApplyFormFields = this.<WrittenApplyFormField, QWrittenApplyFormField>createList("writtenApplyFormFields", WrittenApplyFormField.class, QWrittenApplyFormField.class, PathInits.DIRECT2);

    public QWrittenApplyForm(String variable) {
        super(WrittenApplyForm.class, forVariable(variable));
    }

    public QWrittenApplyForm(Path<? extends WrittenApplyForm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWrittenApplyForm(PathMetadata metadata) {
        super(WrittenApplyForm.class, metadata);
    }

}

