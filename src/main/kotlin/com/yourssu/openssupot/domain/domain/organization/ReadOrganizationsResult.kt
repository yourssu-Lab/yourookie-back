package com.yourssu.openssupot.domain.domain.organization

data class ReadOrganizationsResult(
    val organizationDtos: List<OrganizationDto>,
) {

    companion object {
        fun from(organizations: List<Organization>): ReadOrganizationsResult = ReadOrganizationsResult(
            organizations.map {
                OrganizationDto.from(it)
            }
        )
    }
}
