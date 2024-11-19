package com.yourssu.openssupot.storage.domain.organization

import com.yourssu.openssupot.domain.domain.organization.Hashtag
import org.springframework.data.jpa.repository.JpaRepository

interface JpaHashtagRepository : JpaRepository<Hashtag, Long> {
    fun findByName(name: String): Hashtag?
}
