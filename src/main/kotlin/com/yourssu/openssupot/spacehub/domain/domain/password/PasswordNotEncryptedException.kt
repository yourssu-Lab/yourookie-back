package com.yourssu.openssupot.spacehub.domain.domain.password

class PasswordNotEncryptedException(
    override val message: String
) : RuntimeException(message)
