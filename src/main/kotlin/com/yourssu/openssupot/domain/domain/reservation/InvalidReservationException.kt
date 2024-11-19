package com.yourssu.openssupot.domain.domain.reservation

class InvalidReservationException(
    override val message: String
) : RuntimeException(message)
