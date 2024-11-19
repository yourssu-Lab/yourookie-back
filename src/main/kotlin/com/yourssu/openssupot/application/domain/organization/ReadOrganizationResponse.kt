package com.yourssu.openssupot.application.domain.organization

import com.yourssu.openssupot.domain.domain.organization.OrganizationDto

data class ReadOrganizationResponse(

    val id: Long,
    val name: String,
    val logoImageUrl: String?,
    val description: String?,
) {

    companion object {
        fun from(organizationDto: OrganizationDto) = ReadOrganizationResponse(
            id = organizationDto.id,
            name = organizationDto.name,
            logoImageUrl = organizationDto.logoImageUrl,
            description = organizationDto.description,
        )
    }
}
