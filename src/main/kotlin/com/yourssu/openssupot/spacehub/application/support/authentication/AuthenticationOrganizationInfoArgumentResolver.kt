package com.yourssu.openssupot.spacehub.application.support.authentication

import com.yourssu.openssupot.spacehub.domain.domain.authentication.PrivateClaims
import com.yourssu.openssupot.spacehub.domain.domain.organization.OrganizationRepository
import com.yourssu.openssupot.spacehub.domain.support.security.token.InvalidTokenException
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenDecoder
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenType
import io.jsonwebtoken.Claims
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthenticationOrganizationInfoArgumentResolver(
    private val tokenDecoder: TokenDecoder,
    private val organizationRepository: OrganizationRepository,
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthenticationOrganization::class.java) &&
                parameter.parameterType == AuthenticationOrganizationInfo::class.java
    }

    override fun resolveArgument(
        @NonNull parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val accessToken: String? = webRequest.getHeader(HttpHeaders.AUTHORIZATION)
        if (accessToken.isNullOrBlank()) {
            if (isRequired(parameter)) {
                throw LoginRequiredException("로그인이 필요한 기능입니다.")
            }

            return null
        }

        val privateClaims: PrivateClaims = decode(accessToken)

        if (!organizationRepository.existsById(privateClaims.organizationId)) {
            throw NoSuchOrganizationException("존재하지 않는 단체의 토큰입니다.")
        }

        return AuthenticationOrganizationInfo(privateClaims.organizationId)
    }

    private fun isRequired(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(AuthenticationOrganization::class.java)
            ?.required ?: true
    }

    private fun decode(accessToken: String): PrivateClaims {
        val claims: Claims = tokenDecoder.decode(TokenType.ACCESS, accessToken)
            ?: throw InvalidTokenException("유효한 토큰이 아닙니다.")

        return PrivateClaims.from(claims)
    }
}
