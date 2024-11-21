package com.yourssu.openssupot.domain.domain.password

class InvalidPasswordException(
    override val message: String
) : RuntimeException(message)
