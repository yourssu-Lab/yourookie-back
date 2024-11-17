package com.yourssu.openssupot.domain.domain.authentication

import java.time.LocalDateTime

data class LoginCommand(
    val requestTime: LocalDateTime,
    val email: String,
    val password: String,
)
