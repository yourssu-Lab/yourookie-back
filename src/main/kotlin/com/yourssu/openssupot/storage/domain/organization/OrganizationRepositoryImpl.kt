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

    override fun existsById(id: Long): Boolean {
        return jpaOrganizationRepository.existsById(id)
    }

    override fun existsByEmail(email: String): Boolean {
        return jpaOrganizationRepository.existsByEmail(email)
    }

    override fun findByEmail(email: String): Organization? {
        return jpaOrganizationRepository.findByEmail(email)?.toDomain()
    }

    override fun searchByNameKeyword(keyword: String): List<Organization> {
        return jpaOrganizationRepository.searchByNameKeyword(keyword).map { it.toDomain() }
    }
}
