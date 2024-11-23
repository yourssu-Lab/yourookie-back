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

        val seminarRoomsResponse: SeminarRoomsResponse = getSeminarRoomsResponse(date)
        seminarRooms.addAll(seminarRoomsResponse.data.list.map {
            SeminarRoom.from(it, RoomType.SEMINAR_ROOM)
        })

        val openSeminarRoomsResponse: SeminarRoomsResponse = getOpenSeminarRoomsResponse(date)
        seminarRooms.addAll(openSeminarRoomsResponse.data.list.map {
            SeminarRoom.from(it, RoomType.OPEN_SEMINAR_ROOM)
        })

        return seminarRooms
    }

    private fun getSeminarRoomsResponse(
        date: LocalDate,
        isRecursive: Boolean = false,
    ): SeminarRoomsResponse {
        val seminarRoomsResponse: SeminarRoomsResponse = oasisClient.getSeminarRooms(
            accessToken = oasisTokenProvider.getAccessToken(),
            hopeDate = date.toString(),
        )
        if (!seminarRoomsResponse.success) {
            check(!isRecursive) { "Failed to get seminar rooms" }

            oasisTokenProvider.invalidateAccessToken()
            oasisTokenProvider.fetchNewAccessToken()

            return getSeminarRoomsResponse(date, true)
        }

        return seminarRoomsResponse
    }

    private fun getOpenSeminarRoomsResponse(
        date: LocalDate,
        isRecursive: Boolean = false,
    ): SeminarRoomsResponse {
        val openSeminarRoomsResponse: SeminarRoomsResponse = oasisClient.getOpenSeminarRooms(
            accessToken = oasisTokenProvider.getAccessToken(),
            hopeDate = date.toString(),
        )
        if (!openSeminarRoomsResponse.success) {
            check(!isRecursive) { "Failed to get open seminar rooms" }

            oasisTokenProvider.invalidateAccessToken()
            oasisTokenProvider.fetchNewAccessToken()

            return getOpenSeminarRoomsResponse(date, true)
        }

        return openSeminarRoomsResponse
    }
}
