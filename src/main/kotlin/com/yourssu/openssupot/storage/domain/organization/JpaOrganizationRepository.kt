package com.yourssu.openssupot.storage.domain.organization

import org.springframework.data.jpa.repository.JpaRepository

interface JpaOrganizationRepository : JpaRepository<OrganizationEntity, Long> {

    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): OrganizationEntity?
}
