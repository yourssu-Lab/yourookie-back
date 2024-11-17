package com.yourssu.openssupot.application.domain.organization

import com.yourssu.openssupot.domain.domain.organization.OrganizationService
import jakarta.validation.Valid
import java.net.URI
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/organizations")
class OrganizationController(
    private val organizationService: OrganizationService
) {

    @PostMapping
    fun create(
        @RequestPart(required = false) image: MultipartFile?,
        @RequestPart @Valid request: CreateOrganizationRequest,
    ) : ResponseEntity<Unit> {
        val organizationId = organizationService.create(request.toCommand(image))

        return ResponseEntity.created(URI.create("/organizations/$organizationId")).build()
    }
}
