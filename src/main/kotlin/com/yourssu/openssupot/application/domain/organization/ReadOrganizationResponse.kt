package com.yourssu.openssupot.application.domain.organization

import com.yourssu.openssupot.domain.domain.organization.ReadOrganizationsResult

data class ReadOrganizationResponse(

    val id: Long,
    val name: String,
    val logoImageUrl: String?,
    val description: String?,
) {
    companion object {
        fun from(result: ReadOrganizationsResult): List<ReadOrganizationResponse> {
            return result.organizationDtos.map {
                ReadOrganizationResponse(
                    id = it.id,
                    name = it.name,
                    logoImageUrl = it.logoImageUrl,
                    description = it.description,
                )
            }
        }
    }
}
