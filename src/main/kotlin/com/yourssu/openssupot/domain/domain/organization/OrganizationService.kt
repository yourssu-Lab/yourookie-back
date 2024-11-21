package com.yourssu.openssupot.domain.domain.organization

import com.yourssu.openssupot.domain.domain.file.FileProcessor
import com.yourssu.openssupot.domain.domain.password.PasswordFormat
import com.yourssu.openssupot.domain.domain.password.PasswordValidator
import com.yourssu.openssupot.domain.support.security.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OrganizationService (
    private val fileProcessor: FileProcessor,
    private val passwordEncoder: PasswordEncoder,
    private val organizationWriter: OrganizationWriter,
    private val organizationReader: OrganizationReader,
) {

    fun create(
        command: CreateOrganizationCommand,
    ): Long {
        PasswordValidator.validate(PasswordFormat.ORGANIZATION_PASSWORD, command.rawPassword)
        if (organizationReader.existByEmail(command.email)) {
            throw DuplicateEmailException("이미 존재하는 이메일입니다.")
        }
        val encryptedPassword: String = passwordEncoder.encode(command.rawPassword)
        val encryptedReservationPassword: String = passwordEncoder.encode(command.rawReservationPassword)

        val logoImageUrl: String = command.logoImage?.let {
            fileProcessor.upload(it)
        } ?: fileProcessor.getDefaultOrganizationImageUrl()

        val savedOrganization = organizationWriter.write(
            command,
            logoImageUrl,
            encryptedPassword,
            encryptedReservationPassword
        )

        return savedOrganization.id!!
    }

    fun readById(organizationId: Long): OrganizationDto {
        val organization: Organization = organizationReader.getById(organizationId)

        return OrganizationDto.from(organization)
    }

    fun checkIsUnique(email: String): Boolean {
        return !organizationReader.existByEmail(email)
    }

    fun searchByNameKeyword(nameKeyword: String): ReadOrganizationsResult {
        val organizations: List<Organization> = organizationReader.searchByNameKeyword(nameKeyword)

        return ReadOrganizationsResult.from(organizations)
    }

    fun update(requestOrganizationId: Long, command: UpdateOrganizationCommand) {
        if (requestOrganizationId != command.targetOrganizationId) {
            throw UnauthorizedOrganizationException("본인의 단체 정보만 수정할 수 있습니다.")
        }
        val organization: Organization = organizationReader.getById(command.targetOrganizationId)

        val toUpdate: Organization = organization.updateAndReturnNew(
            name = OrganizationName(command.name),
            logoImageUrl = command.logoImage?.let { fileProcessor.upload(it) },
            description = command.description,
            encryptedReservationPassword = command.rawReservationPassword?.let { passwordEncoder.encode(it) },
        )

        organizationWriter.update(toUpdate, command.hashtags)
    }
}
