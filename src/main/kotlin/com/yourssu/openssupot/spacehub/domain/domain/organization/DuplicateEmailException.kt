package com.yourssu.openssupot.spacehub.domain.domain.organization

class DuplicateEmailException(
    override val message: String
) : RuntimeException(message)
