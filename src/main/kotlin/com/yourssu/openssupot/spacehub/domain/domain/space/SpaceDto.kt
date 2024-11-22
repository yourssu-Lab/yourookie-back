package com.yourssu.openssupot.spacehub.domain.domain.space

import com.yourssu.openssupot.spacehub.domain.domain.organization.OrganizationDto
import java.time.LocalTime

data class SpaceDto(

    val id: Long,
    val organization: OrganizationDto,
    val name: String,
    val location: String,
    val spaceImageUrl: String? = null,
    val openingTime: LocalTime,
    val closingTime: LocalTime,
    val capacity: Int,
) {

    companion object {
        fun from(space: Space): SpaceDto = SpaceDto(
            id = space.id!!,
            organization = OrganizationDto.from(space.organization),
            name = space.name,
            location = space.location,
            spaceImageUrl = space.spaceImageUrl,
            openingTime = space.getOpeningTime(),
            closingTime = space.getClosingTime(),
            capacity = space.getCapacityValue(),
        )
    }
}
