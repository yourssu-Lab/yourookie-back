package com.yourssu.openssupot.domain.domain.space

import com.yourssu.openssupot.domain.domain.organization.OrganizationDto

data class ReadSpacesResult(

    val spaceDtos: List<SpaceDto>,
) {

    companion object {
        fun from(spaces: List<Space>): ReadSpacesResult = ReadSpacesResult(
            spaces.map {
                SpaceDto(
                    id = it.id!!,
                    organization = OrganizationDto.from(it.organization),
                    name = it.name,
                    location = it.location,
                    spaceImageUrl = it.spaceImageUrl,
                    openingTime = it.getOpeningTime(),
                    closingTime = it.getClosingTime(),
                    capacity = it.getCapacityValue(),
                )
            }
        )
    }
}
