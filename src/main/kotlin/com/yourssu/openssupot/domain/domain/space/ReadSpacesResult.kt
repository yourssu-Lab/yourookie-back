package com.yourssu.openssupot.domain.domain.space

data class ReadSpacesResult(

    val spaceDtos: List<SpaceDto>,
) {

    companion object {
        fun from(spaces: List<Space>): ReadSpacesResult = ReadSpacesResult(spaces.map { SpaceDto.from(it) })
    }
}
