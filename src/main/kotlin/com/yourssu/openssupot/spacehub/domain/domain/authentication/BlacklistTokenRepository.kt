package com.yourssu.openssupot.spacehub.domain.domain.authentication

interface BlacklistTokenRepository {

    fun saveAll(blacklistTokens: List<BlacklistToken>): List<BlacklistToken>
    fun existsByOrganizationIdAndToken(organizationId: Long, targetToken: String): Boolean
}
