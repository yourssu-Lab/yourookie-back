package com.yourssu.openssupot.domain.domain.organization

import com.yourssu.openssupot.domain.domain.file.FileUploader
import com.yourssu.openssupot.domain.support.security.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OrganizationService (
    private val fileUploader: FileUploader,
    private val passwordEncoder: PasswordEncoder,
    private val organizationWriter: OrganizationWriter,
    private val organizationReader: OrganizationReader,
) {

    fun create(
        command: CreateOrganizationCommand,
    ): Long {
        if (organizationReader.existByEmail(command.email)) {
            throw DuplicateEmailException("이미 존재하는 이메일입니다.")
        }
        val encryptedPassword: String = passwordEncoder.encode(command.rawPassword)
        val encryptedReservationPassword: String = passwordEncoder.encode(command.rawReservationPassword)
        var logoImageUrl: String? = null
        if (command.logoImage != null) {
            logoImageUrl = fileUploader.upload(command.logoImage)
        }

        val savedOrganization = organizationWriter.write(
            command,
            logoImageUrl,
            encryptedPassword,
            encryptedReservationPassword
        )

        return savedOrganization.id!!
    }

    fun checkIsUnique(email: String): Boolean {
        return !organizationReader.existByEmail(email)
    }
}
