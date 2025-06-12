package com.yourssu.openssupot.spacehub.application.domain.authentication

import com.yourssu.openssupot.spacehub.domain.domain.authentication.AuthenticationService
import com.yourssu.openssupot.spacehub.domain.domain.authentication.LoginResultDto
import com.yourssu.openssupot.spacehub.domain.domain.authentication.TokenDto
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.time.LocalDateTime
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Authentication", description = "인증 API")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {

    @Operation(
        summary = "회원가입",
    )
    @PostMapping("/login")
    fun login(
        @RequestBody @Valid request: LoginRequest,
    ): ResponseEntity<LoginResponse> {
        val loginResultDto: LoginResultDto = authenticationService.login(request.toCommand())
        val response = LoginResponse.from(loginResultDto)

        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "로그아웃",
    )
    @PostMapping("/logout")
    fun logout(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestBody @Valid request: LogoutRequest,
    ): ResponseEntity<Unit> {
        authenticationService.logout(accessToken, request.refreshToken)

        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "토큰 유효성 검사",
    )
    @GetMapping("/token-validate")
    fun validateToken(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
    ): ResponseEntity<ValidateTokenResponse> {
        val validated: Boolean = authenticationService.isValidToken(TokenType.ACCESS, accessToken)
        val response = ValidateTokenResponse(validated)

        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "토큰 갱신",
    )
    @PostMapping("/token-refresh")
    fun refreshToken(
        @RequestBody @Valid request: TokenRefreshRequest,
    ): ResponseEntity<TokenRefreshResponse> {
        val requestTime = LocalDateTime.now()
        val tokenDto: TokenDto = authenticationService.refreshToken(requestTime, request.refreshToken)
        val response = TokenRefreshResponse.from(tokenDto)

        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "회원탈퇴",
    )
    @PostMapping("/withdrawal")
    fun withdraw(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestBody @Valid request: WithdrawalRequest,
    ): ResponseEntity<Unit> {
        authenticationService.withdraw(accessToken, request.refreshToken)

        return ResponseEntity.noContent().build()
    }
}
