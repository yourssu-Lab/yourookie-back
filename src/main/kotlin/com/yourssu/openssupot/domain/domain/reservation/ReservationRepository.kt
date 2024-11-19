package com.yourssu.openssupot.domain.domain.reservation

interface ReservationRepository {

    fun save(reservation: Reservation): Reservation
}
