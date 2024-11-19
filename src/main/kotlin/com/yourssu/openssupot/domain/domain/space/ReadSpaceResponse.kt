package com.yourssu.openssupot.domain.domain.space

import java.time.LocalTime

data class ReadSpaceResponse(

    val id: Long,
    val name: String,
    val location: String,
    val spaceImageUrl: String?,
    val openingTime: LocalTime,
    val closingTime: LocalTime,
    val capacity: Int,
) {

    companion object {
        fun from(spaceDto: SpaceDto) = ReadSpaceResponse(
            id = spaceDto.id!!,
            name = spaceDto.name,
            location = spaceDto.location,
            spaceImageUrl = spaceDto.spaceImageUrl,
            openingTime = spaceDto.openingTime,
            closingTime = spaceDto.closingTime,
            capacity = spaceDto.capacity,
        )
    }
}
