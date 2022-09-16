package com.mohaeng.meeting.domain.meeting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMeeting is a Querydsl query type for Meeting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMeeting extends EntityPathBase<Meeting> {

    private static final long serialVersionUID = -1213852994L;

    public static final QMeeting meeting = new QMeeting("meeting");

    public final com.mohaeng.meeting.configuration.jpa.QBaseEntity _super = new com.mohaeng.meeting.configuration.jpa.QBaseEntity(this);

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public QMeeting(String variable) {
        super(Meeting.class, forVariable(variable));
    }

    public QMeeting(Path<? extends Meeting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeeting(PathMetadata metadata) {
        super(Meeting.class, metadata);
    }

}

