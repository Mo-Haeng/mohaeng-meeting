package com.mohaeng.meeting.query.data.meetingrole;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mohang.meeting.query.data.meetingrole.QMeetingRoleData is a Querydsl Projection type for MeetingRoleData
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMeetingRoleData extends ConstructorExpression<MeetingRoleData> {

    private static final long serialVersionUID = 551830624L;

    public QMeetingRoleData(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<com.mohaeng.meeting.domain.meetingrole.enums.MeetingAuthority> authority, com.querydsl.core.types.Expression<Long> meetingId, com.querydsl.core.types.Expression<Boolean> isParticipantDefault) {
        super(MeetingRoleData.class, new Class<?>[]{long.class, String.class, com.mohaeng.meeting.domain.meetingrole.enums.MeetingAuthority.class, long.class, boolean.class}, id, name, authority, meetingId, isParticipantDefault);
    }

}

