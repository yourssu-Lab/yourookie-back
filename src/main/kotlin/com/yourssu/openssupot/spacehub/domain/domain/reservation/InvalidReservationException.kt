package com.yourssu.openssupot.spacehub.domain.domain.reservation

class InvalidReservationException(
    override val message: String
) : RuntimeException(message)
