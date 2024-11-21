package com.yourssu.openssupot.domain.domain.reservation

import java.time.LocalDateTime

data class CreateReservationCommand(

    val spaceId: Long,
    val bookerName: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val password: String,
    val rawPersonalPassword: String,
)
