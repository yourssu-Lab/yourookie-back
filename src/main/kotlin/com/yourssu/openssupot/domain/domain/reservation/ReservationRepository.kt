package com.yourssu.openssupot.domain.domain.reservation

import java.time.LocalDateTime

interface ReservationRepository {

    fun save(reservation: Reservation): Reservation

    fun existsBySpaceIdAndDateTimeRange(
        spaceId: Long,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Boolean

    fun findAllBySpaceIdAndDateTimeRange(
        spaceId: Long,
        startOfDay: LocalDateTime,
        endOfDay: LocalDateTime
    ): List<Reservation>
}
