package com.yourssu.openssupot.spacehub.domain.support.security.password

class PasswordEncodingFailureException(
    override val message: String
) : RuntimeException(message)
