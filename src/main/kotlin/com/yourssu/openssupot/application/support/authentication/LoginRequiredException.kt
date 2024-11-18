package com.yourssu.openssupot.application.support.authentication

class LoginRequiredException(
    override val message: String
) : RuntimeException(message)
