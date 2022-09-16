package com.mohaeng.meeting.query.data.applyform;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mohang.meeting.query.data.applyform.QApplyFormData is a Querydsl Projection type for ApplyFormData
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QApplyFormData extends ConstructorExpression<ApplyFormData> {

    private static final long serialVersionUID = -457229342L;

    public QApplyFormData(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> createdAt, com.querydsl.core.types.Expression<String> modifiedAt, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Long> meetingId) {
        super(ApplyFormData.class, new Class<?>[]{long.class, String.class, String.class, String.class, long.class}, id, createdAt, modifiedAt, name, meetingId);
    }

}

