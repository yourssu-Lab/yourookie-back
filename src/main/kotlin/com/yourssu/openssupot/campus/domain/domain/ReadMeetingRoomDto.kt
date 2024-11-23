package com.yourssu.openssupot.campus.domain.domain

import com.yourssu.openssupot.campus.domain.domain.oasis.SeminarRoom
import com.yourssu.openssupot.campus.domain.domain.startupportal.StartupSpace

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

        fun from(startupSpace: StartupSpace, reservationUrl: String): ReadMeetingRoomDto {
            return ReadMeetingRoomDto(
                id = startupSpace.id,
                name = startupSpace.spaceType.spaceName,
                spaceImageUrl = startupSpace.spaceType.spaceImageUrl,
                location = startupSpace.spaceType.location,
                operatingTime = startupSpace.spaceType.operatingTime,
                capacity = startupSpace.spaceType.capacity,
                reservationUrl = reservationUrl,
            )
        }
    }
}
