package com.yourssu.openssupot.domain.domain.authentication

import com.yourssu.openssupot.domain.domain.organization.Organization
import com.yourssu.openssupot.domain.domain.organization.OrganizationReader
import com.yourssu.openssupot.domain.support.security.password.PasswordEncoder
import com.yourssu.openssupot.domain.support.security.token.TokenEncoder
import com.yourssu.openssupot.domain.support.security.token.TokenType
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val organizationReader: OrganizationReader,
    private val passwordEncoder: PasswordEncoder,
    private val tokenEncoder: TokenEncoder,
) {

    fun login(loginCommand: LoginCommand): LoginResultDto {
        val organization: Organization = organizationReader.getByEmail(loginCommand.email)
        if (!passwordEncoder.matches(loginCommand.password, organization.encryptedPassword)) {
            throw PasswordNotMatchException("비밀번호가 일치하지 않습니다.")
        }

        val privateClaims = PrivateClaims(organization.id!!)
        val tokenDto: TokenDto = generateTokens(loginCommand.requestTime, privateClaims)

        return LoginResultDto(organization.id, organization.getNameValue(), tokenDto)
    }

    private fun generateTokens(time: LocalDateTime, privateClaims: PrivateClaims): TokenDto {
        val accessToken: String = tokenEncoder.encode(time, TokenType.ACCESS, privateClaims.toMap())
        val refreshToken: String = tokenEncoder.encode(time, TokenType.REFRESH, privateClaims.toMap())

        return TokenDto(accessToken, refreshToken)
    }
}
