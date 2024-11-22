package com.yourssu.openssupot.spacehub.application.domain.authentication

import com.yourssu.openssupot.spacehub.domain.domain.authentication.TokenDto

data class TokenRefreshResponse(

    val accessToken: String,
    val refreshToken: String,
) {

    companion object {
        fun from(tokenDto: TokenDto) = TokenRefreshResponse(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken,
        )
    }
}
