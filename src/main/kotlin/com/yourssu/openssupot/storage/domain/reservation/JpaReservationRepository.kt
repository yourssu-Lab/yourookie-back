package com.yourssu.openssupot.storage.domain.reservation

import java.time.LocalDateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaReservationRepository : JpaRepository<ReservationEntity, Long> {

    @Query(
        """
        SELECT COUNT(r) > 0
        FROM ReservationEntity r
        WHERE r.space.id = :spaceId 
        AND (
            (r.startDateTime < :startDateTime AND :startDateTime < r.endDateTime)
            OR (r.startDateTime < :endDateTime AND :endDateTime < r.endDateTime)
        )
    """
    )
    fun existsBySpaceIdAndDateRange(
        spaceId: Long,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Boolean
}
