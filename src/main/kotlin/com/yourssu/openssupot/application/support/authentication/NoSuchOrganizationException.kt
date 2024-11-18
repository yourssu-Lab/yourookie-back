package com.yourssu.openssupot.application.support.authentication

class NoSuchOrganizationException(
    override val message: String
) : RuntimeException(message)
