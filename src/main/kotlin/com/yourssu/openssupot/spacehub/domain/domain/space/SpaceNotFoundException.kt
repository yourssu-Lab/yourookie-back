package com.yourssu.openssupot.spacehub.domain.domain.space

class SpaceNotFoundException(
    override val message: String
) : RuntimeException(message)
