package com.yourssu.openssupot.domain.domain.file

class UnsupportedFileExtensionException(
    override val message: String
) : RuntimeException(message)
