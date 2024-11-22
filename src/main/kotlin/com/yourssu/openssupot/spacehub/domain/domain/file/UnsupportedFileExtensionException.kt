package com.yourssu.openssupot.spacehub.domain.domain.file

class UnsupportedFileExtensionException(
    override val message: String
) : RuntimeException(message)
