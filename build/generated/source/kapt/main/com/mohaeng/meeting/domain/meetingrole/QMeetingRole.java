package com.mohaeng.meeting.domain.meetingrole;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMeetingRole is a Querydsl query type for MeetingRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMeetingRole extends EntityPathBase<MeetingRole> {

    private static final long serialVersionUID = -159676598L;

    public static final QMeetingRole meetingRole = new QMeetingRole("meetingRole");

    public final com.mohaeng.meeting.configuration.jpa.QBaseEntity _super = new com.mohaeng.meeting.configuration.jpa.QBaseEntity(this);

    public final EnumPath<com.mohaeng.meeting.domain.meetingrole.enums.MeetingAuthority> authority = createEnum("authority", com.mohaeng.meeting.domain.meetingrole.enums.MeetingAuthority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isParticipantDefault = createBoolean("isParticipantDefault");

    public final NumberPath<Long> meetingId = createNumber("meetingId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public QMeetingRole(String variable) {
        super(MeetingRole.class, forVariable(variable));
    }

    public QMeetingRole(Path<? extends MeetingRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeetingRole(PathMetadata metadata) {
        super(MeetingRole.class, metadata);
    }

}

