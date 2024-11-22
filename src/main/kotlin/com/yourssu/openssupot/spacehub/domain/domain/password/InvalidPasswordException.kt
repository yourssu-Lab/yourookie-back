package com.yourssu.openssupot.spacehub.domain.domain.password

class InvalidPasswordException(
    override val message: String
) : RuntimeException(message)
