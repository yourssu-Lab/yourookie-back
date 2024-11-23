package com.yourssu.openssupot.spacehub.application.support.exception

data class UnauthorizedResponse(
    val message: String,
    val refreshRequired: Boolean
)
