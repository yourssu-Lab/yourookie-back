package com.yourssu.openssupot.domain.domain.organization

interface OrganizationRepository {

    fun save(organization: Organization): Organization
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Organization?
}
