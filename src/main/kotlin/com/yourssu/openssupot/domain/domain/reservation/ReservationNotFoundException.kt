package com.yourssu.openssupot.domain.domain.reservation

class ReservationNotFoundException(
    override val message: String
) : RuntimeException(message)
