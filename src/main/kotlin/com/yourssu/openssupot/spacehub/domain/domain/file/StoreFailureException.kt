package com.yourssu.openssupot.spacehub.domain.domain.file

class StoreFailureException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message)
