package com.yourssu.openssupot.domain.domain.organization

interface HashtagRepository {

    fun save(hashtag: Hashtag): Hashtag
    fun findByName(name: String): Hashtag?
}
