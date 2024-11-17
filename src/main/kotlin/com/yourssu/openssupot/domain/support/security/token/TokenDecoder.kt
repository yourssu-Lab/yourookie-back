package com.yourssu.openssupot.domain.support.security.token

import io.jsonwebtoken.Claims

interface TokenDecoder {

    fun decode(tokenType: TokenType, token: String): Claims?
}
