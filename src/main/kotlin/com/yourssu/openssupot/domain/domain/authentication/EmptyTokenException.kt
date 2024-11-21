package com.yourssu.openssupot.domain.domain.authentication

class EmptyTokenException(
    override val message: String,
) : RuntimeException(message)
