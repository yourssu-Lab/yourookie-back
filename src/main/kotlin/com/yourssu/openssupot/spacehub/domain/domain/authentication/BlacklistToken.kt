package com.yourssu.openssupot.spacehub.domain.domain.authentication

import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenType

class BlacklistToken(
    val id: Long? = null,
    val organizationId: Long,
    val tokenType: TokenType,
    val token: String,
) {

    init {
        if (token.isBlank()) {
            throw EmptyTokenException("토큰이 비어있습니다.")
        }
    }
}
