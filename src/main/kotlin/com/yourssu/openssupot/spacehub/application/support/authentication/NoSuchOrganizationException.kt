package com.yourssu.openssupot.spacehub.application.support.authentication

class NoSuchOrganizationException(
    override val message: String
) : RuntimeException(message)
