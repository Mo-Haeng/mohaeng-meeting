package com.mohaeng.meeting.global.event

/**
 * Created by ShinD on 2022/09/20.
 */
interface Event {

    companion object {
        const val CREATE_MEETING_EVENT = "create-meeting"
        const val UPDATE_MEETING_EVENT = "update-meeting"
        const val DELETE_MEETING_EVENT = "delete-meeting"

        const val CREATE_PARTICIPANT_EVENT = "create-participant"
        const val UPDATE_PARTICIPANT_EVENT = "update-participant"
        const val DELETE_PARTICIPANT_EVENT = "delete-participant"
    }
}