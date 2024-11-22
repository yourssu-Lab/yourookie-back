package com.yourssu.openssupot.spacehub.domain.domain.file

class ReadFailureException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message)
