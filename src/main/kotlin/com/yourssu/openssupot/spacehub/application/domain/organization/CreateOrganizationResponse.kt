package com.yourssu.openssupot.spacehub.application.domain.organization

import com.yourssu.openssupot.spacehub.domain.domain.organization.CreateOrganizationResult

data class CreateOrganizationResponse(

    val id: Long,
    val name: String,
    val accessToken: String,
    val refreshToken: String,
) {

    companion object {
        fun from(result: CreateOrganizationResult) = CreateOrganizationResponse(
            id = result.id,
            name = result.name,
            accessToken = result.tokens.accessToken,
            refreshToken = result.tokens.refreshToken,
        )
    }
}
