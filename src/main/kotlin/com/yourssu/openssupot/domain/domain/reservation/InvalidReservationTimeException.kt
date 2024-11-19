package com.yourssu.openssupot.domain.domain.reservation

class InvalidReservationTimeException(
    override val message: String
) : RuntimeException(message)
