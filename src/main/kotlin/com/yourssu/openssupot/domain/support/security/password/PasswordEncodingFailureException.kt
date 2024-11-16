package com.yourssu.openssupot.domain.support.security.password

class PasswordEncodingFailureException(
    override val message: String
) : RuntimeException(message)
