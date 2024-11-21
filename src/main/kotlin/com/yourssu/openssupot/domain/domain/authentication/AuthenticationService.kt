package com.yourssu.openssupot.domain.domain.authentication

import com.yourssu.openssupot.domain.domain.organization.Organization
import com.yourssu.openssupot.domain.domain.organization.OrganizationReader
import com.yourssu.openssupot.domain.support.security.password.PasswordEncoder
import com.yourssu.openssupot.domain.support.security.token.InvalidTokenException
import com.yourssu.openssupot.domain.support.security.token.TokenDecoder
import com.yourssu.openssupot.domain.support.security.token.TokenEncoder
import com.yourssu.openssupot.domain.support.security.token.TokenType
import io.jsonwebtoken.Claims
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val organizationReader: OrganizationReader,
    private val blacklistTokenWriter: BlacklistTokenWriter,
    private val blacklistTokenReader: BlacklistTokenReader,
    private val passwordEncoder: PasswordEncoder,
    private val tokenEncoder: TokenEncoder,
    private val tokenDecoder: TokenDecoder,
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

    fun logout(accessToken: String, refreshToken: String) {
        val claims: Claims = tokenDecoder.decode(TokenType.ACCESS, accessToken)
            ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")
        val organizationId = PrivateClaims.from(claims).organizationId

        val blacklistTokens: MutableList<BlacklistToken>  = mutableListOf()
        blacklistTokens.add(BlacklistToken(
            organizationId = organizationId,
            tokenType = TokenType.ACCESS,
            token = accessToken)
        )
        if (isValidToken(TokenType.REFRESH, refreshToken)) {
            blacklistTokens.add(BlacklistToken(
                organizationId = organizationId,
                tokenType = TokenType.REFRESH,
                token = refreshToken)
            )
        }

        blacklistTokenWriter.write(blacklistTokens)
    }

    private fun isValidToken(tokenType: TokenType, targetToken: String): Boolean {
        if (targetToken.isBlank()) {
            return false
        }

        return tokenDecoder.decode(tokenType, targetToken) != null
    }

    fun isBlacklisted(organizationId: Long, targetToken: String): Boolean {
        return blacklistTokenReader.existsByOrganizationIdAndTargetToken(organizationId, targetToken)
    }
}
