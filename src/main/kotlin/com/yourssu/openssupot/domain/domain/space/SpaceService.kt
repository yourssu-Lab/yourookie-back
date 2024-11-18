package com.yourssu.openssupot.domain.domain.space

import com.yourssu.openssupot.domain.domain.file.FileUploader
import com.yourssu.openssupot.domain.domain.organization.Organization
import com.yourssu.openssupot.domain.domain.organization.OrganizationReader
import org.springframework.stereotype.Service

@Service
class SpaceService(
    private val fileUploader: FileUploader,
    private val organizationReader: OrganizationReader,
    private val spaceWriter: SpaceWriter,
) {

    fun create(
        command: CreateSpaceCommand
    ): Long {
        val organization: Organization = organizationReader.getById(command.organizationId)
        val spaceImageUrl: String? = command.spaceImage?.let { fileUploader.upload(it) }
        val savedSpace = spaceWriter.write(command, organization, spaceImageUrl)

        return savedSpace.id!!
    }
}
