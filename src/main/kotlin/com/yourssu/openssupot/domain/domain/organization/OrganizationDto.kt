package com.yourssu.openssupot.domain.domain.organization

data class OrganizationDto(

    val id: Long,
    val email: String,
    val name: String,
    val logoImageUrl: String?,
    val description: String?,
) {

    companion object {
        fun from(organization: Organization): OrganizationDto = OrganizationDto(
            id = organization.id!!,
            email = organization.getEmailValue(),
            name = organization.getNameValue(),
            logoImageUrl = organization.logoImageUrl,
            description = organization.description,
        )
    }
}
