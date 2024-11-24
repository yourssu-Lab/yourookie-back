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
        isRecursive: Boolean = false,
    ): SeminarRoomsResponse {
        val seminarRoomsResponse: SeminarRoomsResponse = oasisClient.getSeminarRooms(
            accessToken = oasisTokenProvider.getAccessToken(),
            roomTypeId = roomTypeId.toLong(),
            hopeDate = date.toString(),
        )
        if (!seminarRoomsResponse.success) {
            check(!isRecursive) { "Failed to get seminar rooms" }

            oasisTokenProvider.invalidateAccessToken()
            oasisTokenProvider.fetchNewAccessToken()

            return getSeminarRoomsResponse(roomTypeId, date, true)
        }

        return seminarRoomsResponse
    }
}
