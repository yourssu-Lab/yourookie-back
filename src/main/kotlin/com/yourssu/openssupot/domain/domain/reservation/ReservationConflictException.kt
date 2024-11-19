package com.yourssu.openssupot.domain.domain.reservation

class ReservationConflictException(
    override val message: String
) : RuntimeException(message)
