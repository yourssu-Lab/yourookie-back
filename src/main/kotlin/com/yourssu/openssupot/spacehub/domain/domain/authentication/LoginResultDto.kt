package com.yourssu.openssupot.spacehub.domain.domain.authentication

data class LoginResultDto(
    val id: Long,
    val name: String,
    val tokens: TokenDto,
)
