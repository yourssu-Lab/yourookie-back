package com.yourssu.openssupot.campus.domain.domain.oasis

import java.time.LocalDate
import org.springframework.stereotype.Component

@Component
class OasisReader(
    private val oasisTokenProvider: OasisTokenProvider,
    private val oasisClient: OasisClient,
) {

    fun getAllByDate(date: LocalDate): List<SeminarRoom> {
        val seminarRooms = mutableListOf<SeminarRoom>()

        for (roomType in RoomType.entries) {
            val seminarRoomsResponse: SeminarRoomsResponse = getSeminarRoomsResponse(roomType.id, date)
            seminarRooms.addAll(seminarRoomsResponse.data.list.map {
                SeminarRoom.from(it, roomType)
            })
        }

        return seminarRooms
    }

    private fun getSeminarRoomsResponse(
        roomTypeId: Int,
        date: LocalDate,
    ): SeminarRoomsResponse {
        try {
            val seminarRoomResponse: SeminarRoomsResponse = getFromOasis(roomTypeId, date)
            if (!seminarRoomResponse.success) {
                oasisTokenProvider.invalidateAccessToken()
                oasisTokenProvider.fetchNewAccessToken()

                return getFromOasis(roomTypeId, date)
            }

            return seminarRoomResponse
        } catch (e: Exception) {
            oasisTokenProvider.invalidateAccessToken()
            oasisTokenProvider.fetchNewAccessToken()

            return getFromOasis(roomTypeId, date)
        }
    }

    private fun getFromOasis(
        roomTypeId: Int,
        date: LocalDate,
    ): SeminarRoomsResponse {
        return oasisClient.getSeminarRooms(
            accessToken = oasisTokenProvider.getAccessToken(),
            roomTypeId = roomTypeId.toLong(),
            hopeDate = date.toString(),
        )
    }
}
