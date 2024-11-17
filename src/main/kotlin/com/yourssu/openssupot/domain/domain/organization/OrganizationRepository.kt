package com.yourssu.openssupot.domain.domain.organization

interface OrganizationRepository {

    fun save(organization: Organization): Organization
}
