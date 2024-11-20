package com.yourssu.openssupot.domain.domain.space

import com.yourssu.openssupot.domain.domain.organization.Organization
import com.yourssu.openssupot.domain.domain.organization.OrganizationDto

data class ReadSpacesResult(

    val organizationDto: OrganizationDto,
    val spaceDtos: List<SpaceDto>,
) {

    companion object {
        fun from(organization: Organization, spaces: List<Space>): ReadSpacesResult = ReadSpacesResult(
            organizationDto = OrganizationDto.from(organization),
            spaceDtos = spaces.map { SpaceDto.from(it) }
        )
    }
}
