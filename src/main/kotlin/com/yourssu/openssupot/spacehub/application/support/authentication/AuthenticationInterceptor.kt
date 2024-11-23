package com.yourssu.openssupot.spacehub.application.support.authentication

import com.yourssu.openssupot.spacehub.domain.domain.authentication.AuthenticationService
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenType
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthenticationInterceptor(
    private val authenticationService: AuthenticationService,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val accessToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (accessToken.isNullOrEmpty()) {
            return true
        }

        authenticationService.getValidOrganizationId(TokenType.ACCESS, accessToken)

        return true
    }
}
