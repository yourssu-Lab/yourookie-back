package com.yourssu.openssupot.domain.domain.organization

data class ReadOrganizationResult(
    val organizationDtos: List<OrganizationDto>,
) {

    companion object {
        fun from(organizations: List<Organization>): ReadOrganizationResult = ReadOrganizationResult(
            organizations.map {
                OrganizationDto(
                    id = it.id!!,
                    email = it.getEmailValue(),
                    name = it.getNameValue(),
                    logoImageUrl = it.logoImageUrl,
                    description = it.description,
                )
            }
        )
    }
}
