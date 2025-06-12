package com.yourssu.openssupot.spacehub.application.domain.file

import com.yourssu.openssupot.spacehub.domain.domain.file.FileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/files")
@Tag(name = "File", description = "파일 API")
class FileController(
    private val fileService: FileService,
) {

    @Operation(
        summary = "서버에 저장된 파일 요청",
    )
    @GetMapping("/{storeName}")
    fun read(@PathVariable storeName: String): ResponseEntity<Resource> {
        val resource: Resource = fileService.read(storeName)
        val headers = HttpHeaders()
        headers.contentType = MediaType.IMAGE_JPEG

        return ResponseEntity(resource, headers, HttpStatus.OK)
    }
}
