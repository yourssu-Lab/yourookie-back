package com.yourssu.openssupot.domain.domain.reservation

data class ReadReservationsResult(
    val reservationDtos: List<ReservationDto>,
) {

    companion object {
        fun from(reservations: List<Reservation>): ReadReservationsResult = ReadReservationsResult(
            reservations.map {
                ReservationDto.from(it)
            }
        )
    }
}
