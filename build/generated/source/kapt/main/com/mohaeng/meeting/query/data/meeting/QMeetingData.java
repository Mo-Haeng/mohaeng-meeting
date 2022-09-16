package com.mohaeng.meeting.query.data.meeting;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mohang.meeting.query.data.meeting.QMeetingData is a Querydsl Projection type for MeetingData
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMeetingData extends ConstructorExpression<MeetingData> {

    private static final long serialVersionUID = 1285358036L;

    public QMeetingData(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> description, com.querydsl.core.types.Expression<Integer> capacity, com.querydsl.core.types.Expression<String> createdAt, com.querydsl.core.types.Expression<String> modifiedAt, com.querydsl.core.types.Expression<Long> representativeParticipantId, com.querydsl.core.types.Expression<Long> representativeMemberId, com.querydsl.core.types.Expression<String> representativeNickname) {
        super(MeetingData.class, new Class<?>[]{long.class, String.class, String.class, int.class, String.class, String.class, long.class, long.class, String.class}, id, name, description, capacity, createdAt, modifiedAt, representativeParticipantId, representativeMemberId, representativeNickname);
    }

}

