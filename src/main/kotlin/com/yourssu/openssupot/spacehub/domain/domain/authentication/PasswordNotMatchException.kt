package com.yourssu.openssupot.spacehub.domain.domain.authentication

class PasswordNotMatchException(
    override val message: String
) : RuntimeException(message)
