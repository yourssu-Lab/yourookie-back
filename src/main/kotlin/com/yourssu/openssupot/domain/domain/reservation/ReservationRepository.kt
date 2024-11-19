package com.yourssu.openssupot.domain.domain.reservation

import java.time.LocalDateTime

interface ReservationRepository {

    fun save(reservation: Reservation): Reservation

    fun existsBySpaceIdAndDateRange(
        spaceId: Long,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Boolean
}
