package com.yourssu.openssupot.domain.domain.space

import com.yourssu.openssupot.domain.domain.organization.Organization
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class SpaceReader(
    private val spaceRepository: SpaceRepository
) {

    fun readAllByOrganization(organization: Organization): List<Space> {
        return spaceRepository.findAllByOrganizationId(organization.id!!)
    }
}
