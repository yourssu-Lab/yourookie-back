package com.yourssu.openssupot.spacehub.domain.domain.organization

class UnauthorizedOrganizationException(
    override val message: String,
) : RuntimeException(message)
