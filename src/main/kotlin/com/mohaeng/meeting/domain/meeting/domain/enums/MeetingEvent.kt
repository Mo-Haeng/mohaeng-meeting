package com.mohaeng.meeting.domain.meeting.domain.enums

import com.mohaeng.meeting.global.configuration.event.Event

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