package com.yourssu.openssupot.storage.domain.authentication

import org.springframework.data.jpa.repository.JpaRepository

interface JpaBlacklistTokenRepository : JpaRepository<BlacklistTokenEntity, Long> {
}
