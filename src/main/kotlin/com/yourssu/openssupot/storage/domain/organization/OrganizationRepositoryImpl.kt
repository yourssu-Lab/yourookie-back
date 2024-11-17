package com.yourssu.openssupot.storage.domain.organization

import com.yourssu.openssupot.domain.domain.organization.Organization
import com.yourssu.openssupot.domain.domain.organization.OrganizationRepository
import org.springframework.stereotype.Repository

@Repository
class OrganizationRepositoryImpl(
    private val jpaOrganizationRepository: JpaOrganizationRepository,
) : OrganizationRepository {

    override fun save(organization: Organization): Organization {
        return jpaOrganizationRepository.save(OrganizationEntity.from(organization)).toDomain()
    }
}
