package com.yourssu.openssupot.storage.domain.organization

import com.yourssu.openssupot.domain.domain.organization.OrganizationHashtag
import com.yourssu.openssupot.domain.domain.organization.OrganizationHashtagRepository
import org.springframework.stereotype.Repository

@Repository
class OrganizationHashtagRepositoryImpl(
    private val jpaOrganizationHashtagRepository: JpaOrganizationHashtagRepository
) : OrganizationHashtagRepository {

    override fun save(organizationHashtag: OrganizationHashtag): OrganizationHashtag {
        return jpaOrganizationHashtagRepository.save(OrganizationHashtagEntity.from(organizationHashtag)).toDomain()
    }

    override fun findByOrganizationIdAndHashtagId(
        organizationId: Long,
        hashtagId: Long
    ): OrganizationHashtag? {
        return jpaOrganizationHashtagRepository.findByOrganizationIdAndHashtagId(organizationId, hashtagId)?.toDomain()
    }
}
