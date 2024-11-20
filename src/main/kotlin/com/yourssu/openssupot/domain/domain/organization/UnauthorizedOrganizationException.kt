package com.yourssu.openssupot.domain.domain.organization

class UnauthorizedOrganizationException(
    override val message: String,
): RuntimeException(message)
