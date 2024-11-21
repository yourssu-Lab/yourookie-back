package com.yourssu.openssupot.domain.domain.password

class PasswordNotEncryptedException(
    override val message: String
) : RuntimeException(message)
