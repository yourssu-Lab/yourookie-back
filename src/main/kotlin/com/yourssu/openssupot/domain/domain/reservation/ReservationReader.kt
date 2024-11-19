package com.yourssu.openssupot.domain.domain.reservation

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class ReservationReader(
    private val reservationRepository: ReservationRepository,
) {

    fun isTimeConflict(reservation: Reservation): Boolean {
        return reservationRepository.existsBySpaceIdAndDateTimeRange(
            reservation.space.id!!,
            reservation.getStartDateTime(),
            reservation.getEndDateTime(),
        )
    }
}
