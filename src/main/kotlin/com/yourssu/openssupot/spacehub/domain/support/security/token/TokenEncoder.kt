package com.yourssu.openssupot.spacehub.domain.support.security.token

import java.time.LocalDateTime

interface TokenEncoder {

    fun encode(
        issueTime: LocalDateTime,
        tokenType: TokenType,
        privateClaims: Map<String, Any>
    ): String
}
