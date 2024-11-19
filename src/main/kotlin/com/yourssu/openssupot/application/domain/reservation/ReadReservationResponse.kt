package com.yourssu.openssupot.application.domain.reservation

import com.yourssu.openssupot.domain.domain.reservation.ReservationDto
import java.time.LocalDateTime

data class ReadReservationResponse(
    val id: Long,
    val name: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
) {

    companion object {
        fun from(dto: ReservationDto): ReadReservationResponse {
            return ReadReservationResponse(
                id = dto.id,
                name = dto.bookerName,
                startDateTime = dto.startDateTime,
                endDateTime = dto.endDateTime,
            )
        }
    }
}
