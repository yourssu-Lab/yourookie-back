package com.yourssu.openssupot.domain.domain.file

class StoreFailureException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message)
