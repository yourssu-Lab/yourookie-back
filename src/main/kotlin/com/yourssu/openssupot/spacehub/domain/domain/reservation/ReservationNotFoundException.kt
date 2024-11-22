package com.yourssu.openssupot.spacehub.domain.domain.reservation

class ReservationNotFoundException(
    override val message: String
) : RuntimeException(message)
