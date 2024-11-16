package com.yourssu.openssupot.domain.domain.file

class ReadFailureException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message)
