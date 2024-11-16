package com.yourssu.openssupot.domain.support.security.password

interface PasswordEncoder {

    fun encode(rawPassword: String): String
    fun matches(rawPassword: String, encodedPassword: String): Boolean
}

