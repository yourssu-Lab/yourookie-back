package com.yourssu.openssupot.spacehub.domain.support.security.token

class InvalidTokenException(
    override val message: String
) : RuntimeException(message)
