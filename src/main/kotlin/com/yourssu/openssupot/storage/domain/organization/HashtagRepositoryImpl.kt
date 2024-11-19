package com.yourssu.openssupot.storage.domain.organization

import com.yourssu.openssupot.domain.domain.organization.Hashtag
import com.yourssu.openssupot.domain.domain.organization.HashtagRepository
import org.springframework.stereotype.Repository

@Repository
class HashtagRepositoryImpl(
    private val jpaHashtagRepository: JpaHashtagRepository
) : HashtagRepository {

    override fun save(hashtag: Hashtag): Hashtag {
        return jpaHashtagRepository.save(hashtag)
    }

    override fun findByName(name: String): Hashtag? {
        return jpaHashtagRepository.findByName(name)
    }
}
