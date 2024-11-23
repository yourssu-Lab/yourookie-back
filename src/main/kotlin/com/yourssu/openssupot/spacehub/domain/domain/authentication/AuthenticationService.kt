package com.yourssu.openssupot.spacehub.domain.domain.authentication

import com.yourssu.openssupot.spacehub.application.support.authentication.NoSuchOrganizationException
import com.yourssu.openssupot.spacehub.domain.domain.organization.Organization
import com.yourssu.openssupot.spacehub.domain.domain.organization.OrganizationReader
import com.yourssu.openssupot.spacehub.domain.support.security.password.PasswordEncoder
import com.yourssu.openssupot.spacehub.domain.support.security.token.InvalidTokenException
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenDecoder
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenEncoder
import com.yourssu.openssupot.spacehub.domain.support.security.token.TokenType
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

        val blacklistTokens: MutableList<BlacklistToken> = mutableListOf()
        blacklistTokens.add(
            BlacklistToken(
                organizationId = organizationId,
                tokenType = TokenType.ACCESS,
                token = accessToken
            )
        )
        if (isValidToken(TokenType.REFRESH, refreshToken)) {
            blacklistTokens.add(
                BlacklistToken(
                    organizationId = organizationId,
                    tokenType = TokenType.REFRESH,
                    token = refreshToken
                )
            )
        }

        blacklistTokenWriter.write(blacklistTokens)
    }

    fun decode(tokenType: TokenType, accessToken: String): PrivateClaims {
        val claims: Claims = tokenDecoder.decode(tokenType, accessToken)
            ?: throw InvalidTokenException("유효한 토큰이 아닙니다.")

        return PrivateClaims.from(claims)
    }

    fun getValidOrganizationId(tokenType: TokenType, token: String): Long {
        val claims: Claims = tokenDecoder.decode(tokenType, token)
            ?: throw InvalidTokenException("유효한 토큰이 아닙니다.")

        val organizationId = PrivateClaims.from(claims).organizationId
        if (!existsByOrganizationId(organizationId)) {
            throw NoSuchOrganizationException("존재하지 않는 단체의 토큰입니다.")
        }
        if (isBlacklisted(organizationId, token)) {
            throw InvalidTokenException("로그아웃되었습니다.")
        }

        return organizationId
    }

    fun isValidToken(tokenType: TokenType, targetToken: String): Boolean {
        val claims: Claims = tokenDecoder.decode(tokenType, targetToken)
            ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")
        val organizationId = PrivateClaims.from(claims).organizationId

        return !isBlacklisted(organizationId, targetToken)
    }

    fun existsByOrganizationId(organizationId: Long): Boolean {
        return organizationReader.existsById(organizationId)
    }

    fun isBlacklisted(organizationId: Long, targetToken: String): Boolean {
        return blacklistTokenReader.existsByOrganizationIdAndTargetToken(organizationId, targetToken)
    }

    fun refreshToken(requestTime: LocalDateTime, refreshToken: String): TokenDto {
        val claims: Claims = tokenDecoder.decode(TokenType.REFRESH, refreshToken)
            ?: throw InvalidTokenException("유효한 토큰이 아닙니다.")

        val privateClaims = PrivateClaims.from(claims)

        return generateTokens(requestTime, privateClaims)
    }
}
