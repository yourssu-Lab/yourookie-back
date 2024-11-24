package com.yourssu.openssupot.spacehub.domain.domain.organization

import com.yourssu.openssupot.spacehub.domain.domain.authentication.TokenDto

data class CreateOrganizationResult(
    val id: Long,
    val name: String,
    val tokens: TokenDto,
) {
}
