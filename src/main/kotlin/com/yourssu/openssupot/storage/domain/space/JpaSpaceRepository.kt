package com.yourssu.openssupot.storage.domain.space

import org.springframework.data.jpa.repository.JpaRepository

interface JpaSpaceRepository : JpaRepository<SpaceEntity, Long> {
}
