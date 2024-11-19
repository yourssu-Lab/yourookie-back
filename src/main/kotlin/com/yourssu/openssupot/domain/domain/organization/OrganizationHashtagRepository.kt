package com.yourssu.openssupot.domain.domain.organization

interface OrganizationHashtagRepository {

    fun save(organizationHashtag: OrganizationHashtag): OrganizationHashtag

    fun findByOrganizationIdAndHashtagId(
        organizationId: Long,
        hashtagId: Long
    ): OrganizationHashtag?
}
