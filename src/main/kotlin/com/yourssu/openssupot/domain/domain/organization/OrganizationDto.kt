package com.yourssu.openssupot.domain.domain.organization

data class OrganizationDto(

    val id: Long,
    val email: String,
    val name: String,
    val logoImageUrl: String?,
    val description: String?,
)
