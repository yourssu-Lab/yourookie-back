package com.yourssu.openssupot.storage.domain.organization

import org.springframework.data.jpa.repository.JpaRepository

interface JpaHashtagRepository : JpaRepository<HashtagEntity, Long> {
    fun findByName(name: String): HashtagEntity?
}
