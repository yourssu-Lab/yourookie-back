package com.yourssu.openssupot.domain.domain.organization

class PasswordNotEncryptedException(
    override val message: String
) : RuntimeException(message)
