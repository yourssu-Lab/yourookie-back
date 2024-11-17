package com.yourssu.openssupot.domain.domain.organization

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class OrganizationReader(
    private val organizationRepository: OrganizationRepository
) {

    fun existByEmail(email: String): Boolean {
        return organizationRepository.existsByEmail(email)
    }

    fun getByEmail(email: String): Organization {
        return organizationRepository.findByEmail(email)
            ?: throw OrganizationNotFoundException("$email 로 가입한 이력이 없습니다.")
    }
}
