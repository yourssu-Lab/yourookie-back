package com.yourssu.openssupot.spacehub.domain.domain.organization

interface OrganizationHashtagRepository {

    fun save(organizationHashtag: OrganizationHashtag): OrganizationHashtag

    fun findByOrganizationIdAndHashtagId(
        organizationId: Long,
        hashtagId: Long
    ): OrganizationHashtag?

    fun findAllByOrganizationId(organizationId: Long): List<OrganizationHashtag>

    fun deleteAllByOrganizationId(organizationId: Long)
}
