package com.yourssu.openssupot.spacehub.domain.domain.authentication

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class BlacklistTokenWriter(
    private val blacklistTokenRepository: BlacklistTokenRepository,
) {

    fun write(blacklistTokens: List<BlacklistToken>): List<BlacklistToken> {
        return blacklistTokenRepository.saveAll(blacklistTokens)
    }
}
