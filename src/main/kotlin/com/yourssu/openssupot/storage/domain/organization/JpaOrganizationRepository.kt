package com.yourssu.openssupot.storage.domain.organization

import org.springframework.data.jpa.repository.JpaRepository

interface JpaOrganizationRepository : JpaRepository<OrganizationEntity, Long> {
}
