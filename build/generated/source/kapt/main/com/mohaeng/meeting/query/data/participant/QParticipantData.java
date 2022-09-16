package com.mohaeng.meeting.query.data.participant;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mohang.meeting.query.data.participant.QParticipantData is a Querydsl Projection type for ParticipantData
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QParticipantData extends ConstructorExpression<ParticipantData> {

    private static final long serialVersionUID = -1710735548L;

    public QParticipantData(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> profileImagePath, com.querydsl.core.types.Expression<Long> meetingId, com.querydsl.core.types.Expression<com.mohaeng.meeting.query.data.meetingrole.MeetingRoleData> meetingRoleData) {
        super(ParticipantData.class, new Class<?>[]{long.class, long.class, String.class, String.class, long.class, com.mohaeng.meeting.query.data.meetingrole.MeetingRoleData.class}, id, memberId, nickname, profileImagePath, meetingId, meetingRoleData);
    }

}

