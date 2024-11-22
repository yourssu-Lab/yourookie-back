package com.yourssu.openssupot.spacehub.application.support.authentication

class LoginRequiredException(
    override val message: String
) : RuntimeException(message)
