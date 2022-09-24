package com.mohaeng.meeting.domain.meeting.enums

import com.mohaeng.meeting.configuration.event.Event

/**
 * Created by ShinD on 2022/09/20.
 */
enum class MeetingEvent(

    private val event: String,

    ) : Event {

    CREATE("create-meeting"),
    UPDATE("update-meeting"),
    DELETE("delete-meeting"),
    ;

    override fun event(): String {
        return this.event
    }
}