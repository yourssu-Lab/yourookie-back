package com.yourssu.openssupot.campus.domain.domain

import com.yourssu.openssupot.campus.domain.domain.oasis.SeminarRoom

data class ReadMeetingRoomDto(
    val id: Int,
    val name: String,
    val spaceImageUrl: String,
    val location: String,
    val operatingTime: String,
    val capacity: String,
    val reservationUrl: String,
) {
    companion object {
        fun from(seminarRoom: SeminarRoom, spaceImageUrl: String, reservationUrl: String): ReadMeetingRoomDto {
            return ReadMeetingRoomDto(
                id = seminarRoom.id,
                name = seminarRoom.name,
                spaceImageUrl = spaceImageUrl,
                location = seminarRoom.roomType.location,
                operatingTime = seminarRoom.roomType.operatingTime,
                capacity = seminarRoom.capacity,
                reservationUrl = reservationUrl,
            )
        }
    }
}
