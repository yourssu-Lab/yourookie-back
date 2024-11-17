package com.yourssu.openssupot.domain.domain.organization

class DuplicateEmailException(
    override val message: String
) : RuntimeException(message)
