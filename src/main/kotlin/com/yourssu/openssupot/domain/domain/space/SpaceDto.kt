package com.yourssu.openssupot.domain.domain.space

import com.yourssu.openssupot.domain.domain.organization.OrganizationDto
import java.time.LocalTime

data class SpaceDto(

    val id: Long? = null,
    val organization: OrganizationDto,
    val name: String,
    val location: String,
    val spaceImageUrl: String? = null,
    val openingTime: LocalTime,
    val closingTime: LocalTime,
    val capacity: Int,
) {
}
