package com.yourssu.openssupot.application.domain.authentication

import com.yourssu.openssupot.domain.domain.authentication.AuthenticationService
import com.yourssu.openssupot.domain.domain.authentication.LoginResultDto
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
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

    @PostMapping("/logout")
    fun logout(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestBody @Valid request: LogoutRequest,
    ): ResponseEntity<Unit> {
        authenticationService.logout(accessToken, request.refreshToken)

        return ResponseEntity.noContent().build()
    }
}
