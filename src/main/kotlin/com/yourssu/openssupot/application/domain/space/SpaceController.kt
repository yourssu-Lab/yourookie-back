package com.yourssu.openssupot.application.domain.space

import com.yourssu.openssupot.application.support.authentication.AuthenticationOrganization
import com.yourssu.openssupot.application.support.authentication.AuthenticationOrganizationInfo
import com.yourssu.openssupot.domain.domain.space.CreateSpaceCommand
import com.yourssu.openssupot.domain.domain.space.ReadSpacesResult
import com.yourssu.openssupot.domain.domain.space.SpaceService
import jakarta.validation.Valid
import java.net.URI
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class SpaceController(
    private val spaceService: SpaceService,
) {

    @PostMapping("/spaces")
    fun create(
        @AuthenticationOrganization authInfo: AuthenticationOrganizationInfo,
        @RequestPart(required = false) image: MultipartFile?,
        @RequestPart @Valid request: CreateSpaceRequest,
    ): ResponseEntity<Unit> {
        val organizationId = authInfo.organizationId
        val command: CreateSpaceCommand = request.toCommand(organizationId, image)
        val spaceId = spaceService.create(command)

        return ResponseEntity.created(URI.create("/spaces/$spaceId")).build()
    }

    @GetMapping("/spaces")
    fun readAllByOrganizationId(
        @RequestParam organizationId: Long,
    ): ResponseEntity<List<ReadSpaceResponse>> {
        val spaces: ReadSpacesResult = spaceService.readAllByOrganizationId(organizationId)
        val response: List<ReadSpaceResponse> = spaces.spaceDtos.map { ReadSpaceResponse.from(it) }

        return ResponseEntity.ok(response)
    }
}
