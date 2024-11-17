package com.yourssu.openssupot.domain.support.security.token

class InvalidTokenException(
    override val message: String
) : RuntimeException(message) {
}
