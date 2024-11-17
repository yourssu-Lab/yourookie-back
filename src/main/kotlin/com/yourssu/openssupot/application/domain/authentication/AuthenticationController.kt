package com.yourssu.openssupot.application.domain.authentication

import com.yourssu.openssupot.domain.domain.authentication.AuthenticationService
import com.yourssu.openssupot.domain.domain.authentication.LoginResultDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid request: LoginRequest,
    ): ResponseEntity<LoginResponse> {
        val loginResultDto: LoginResultDto = authenticationService.login(request.toCommand())
        val response = LoginResponse.from(loginResultDto)

        return ResponseEntity.ok(response)
    }
}
