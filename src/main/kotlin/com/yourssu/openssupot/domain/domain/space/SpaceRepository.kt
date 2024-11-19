package com.yourssu.openssupot.domain.domain.space

interface SpaceRepository {

    fun save(space: Space): Space
    fun findAllByOrganizationId(organizationId: Long): List<Space>
}
