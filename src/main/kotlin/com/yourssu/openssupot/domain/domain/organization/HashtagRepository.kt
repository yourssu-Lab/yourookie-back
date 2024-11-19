package com.yourssu.openssupot.domain.domain.organization

interface HashtagRepository {

    fun save(hashtag: Hashtag): Hashtag
    fun findById(id: Long): Hashtag?
    fun findByName(name: String): Hashtag?
}
