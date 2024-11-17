package com.yourssu.openssupot.domain.domain.authentication

class PasswordNotMatchException(
    override val message: String
) : RuntimeException(message)
