package com.yourssu.openssupot.spacehub.domain.domain.authentication

class EmptyTokenException(
    override val message: String,
) : RuntimeException(message)
