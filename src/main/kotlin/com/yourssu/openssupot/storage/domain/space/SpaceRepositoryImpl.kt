package com.yourssu.openssupot.storage.domain.space

import com.yourssu.openssupot.domain.domain.space.Space
import com.yourssu.openssupot.domain.domain.space.SpaceRepository
import org.springframework.stereotype.Repository

@Repository
class SpaceRepositoryImpl(

    private val jpaSpaceRepository: JpaSpaceRepository
) : SpaceRepository {

    override fun save(space: Space): Space {
        return jpaSpaceRepository.save(SpaceEntity.from(space)).toDomain()
    }
}
