package com.yourssu.openssupot.domain.domain.authentication

interface BlacklistTokenRepository {

    fun saveAll(blacklistTokens: List<BlacklistToken>) : List<BlacklistToken>
}
