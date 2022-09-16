package com.mohaeng.meeting.query.data.applyform;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mohang.meeting.query.data.applyform.QApplyFormFieldData is a Querydsl Projection type for ApplyFormFieldData
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QApplyFormFieldData extends ConstructorExpression<ApplyFormFieldData> {

    private static final long serialVersionUID = -793735540L;

    public QApplyFormFieldData(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name) {
        super(ApplyFormFieldData.class, new Class<?>[]{long.class, String.class}, id, name);
    }

}

