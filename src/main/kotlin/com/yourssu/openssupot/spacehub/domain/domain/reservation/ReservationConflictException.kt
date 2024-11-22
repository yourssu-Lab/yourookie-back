package com.yourssu.openssupot.spacehub.domain.domain.reservation

class ReservationConflictException(
    override val message: String
) : RuntimeException(message)
