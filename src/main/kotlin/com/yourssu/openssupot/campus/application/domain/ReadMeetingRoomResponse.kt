package com.yourssu.openssupot.campus.application.domain

import com.yourssu.openssupot.campus.domain.domain.ReadMeetingRoomDto

data class ReadMeetingRoomResponse(
    val name: String,
    val spaceImageUrl: String,
    val location: String,
    val operatingTime: String,
    val capacity: String,
    val reservationUrl: String,
) {
    companion object {
        fun from(dto: ReadMeetingRoomDto): ReadMeetingRoomResponse {
            return ReadMeetingRoomResponse(
                name = dto.name,
                spaceImageUrl = dto.spaceImageUrl,
                location = dto.location,
                operatingTime = dto.operatingTime,
                capacity = dto.capacity,
                reservationUrl = dto.reservationUrl,
            )
        }
    }
}
