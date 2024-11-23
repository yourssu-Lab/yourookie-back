package com.yourssu.openssupot.spacehub.application.support.authentication

import com.yourssu.openssupot.spacehub.domain.domain.authentication.AuthenticationService
import com.yourssu.openssupot.spacehub.domain.domain.authentication.PrivateClaims
import com.yourssu.openssupot.spacehub.domain.support.security.token.InvalidTokenException
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenDecoder
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenType
import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthenticationInterceptor(
    private val tokenDecoder: TokenDecoder,
    private val authenticationService: AuthenticationService,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val accessToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (accessToken.isNullOrEmpty()) {
            return true
        }

        val privateClaims: PrivateClaims = decode(accessToken)
        val organizationId = privateClaims.organizationId

        if (!authenticationService.existsByOrganizationId(organizationId)) {
            throw NoSuchOrganizationException("존재하지 않는 단체의 토큰입니다.")
        }

        if (authenticationService.isBlacklisted(organizationId, accessToken)) {
            throw InvalidTokenException("로그아웃되었습니다.")
        }
        return true
    }

    private fun decode(accessToken: String): PrivateClaims {
        val claims: Claims = tokenDecoder.decode(TokenType.ACCESS, accessToken)
            ?: throw InvalidTokenException("유효한 토큰이 아닙니다.")

        return PrivateClaims.from(claims)
    }
}
