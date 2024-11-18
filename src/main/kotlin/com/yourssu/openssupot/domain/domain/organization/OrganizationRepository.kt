package com.yourssu.openssupot.domain.domain.organization

interface OrganizationRepository {

    fun save(organization: Organization): Organization
    fun existsById(id: Long): Boolean
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Organization?
    fun searchByNameKeyword(keyword: String): List<Organization>
}
