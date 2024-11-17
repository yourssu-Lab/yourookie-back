package com.yourssu.openssupot.domain.domain.organization

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class OrganizationWriter(
    private val organizationRepository: OrganizationRepository,
) {

    fun write(
        command: CreateOrganizationCommand,
        logoImageUrl: String?,
        encryptedPassword: String,
        encryptedReservationPassword: String,
    ): Organization {
        val toSave = Organization(
            email = Email(command.email),
            encryptedPassword = encryptedPassword,
            name = OrganizationName(command.name),
            logoImageUrl = logoImageUrl,
            description = command.description,
            encryptedReservationPassword = encryptedReservationPassword,
        )

        return organizationRepository.save(toSave)
    }
}
