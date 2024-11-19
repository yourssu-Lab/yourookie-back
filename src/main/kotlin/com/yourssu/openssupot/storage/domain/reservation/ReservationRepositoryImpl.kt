package com.yourssu.openssupot.storage.domain.reservation

import com.yourssu.openssupot.domain.domain.reservation.Reservation
import com.yourssu.openssupot.domain.domain.reservation.ReservationRepository
import java.time.LocalDateTime
import org.springframework.stereotype.Repository

@Repository
class ReservationRepositoryImpl(
    private val jpaReservationRepository: JpaReservationRepository,
) : ReservationRepository {

    override fun save(reservation: Reservation): Reservation {
        return jpaReservationRepository.save(ReservationEntity.from(reservation)).toDomain()
    }

    override fun existsBySpaceIdAndDateTimeRange(
        spaceId: Long,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): Boolean {
        return jpaReservationRepository.existsBySpaceIdAndDateTimeRange(spaceId, startDateTime, endDateTime)
    }

    override fun findAllBySpaceIdAndDateTimeRange(
        spaceId: Long,
        startOfDay: LocalDateTime,
        endOfDay: LocalDateTime
    ): List<Reservation> {
        return jpaReservationRepository.findAllBySpaceIdAndDateRange(spaceId, startOfDay, endOfDay)
            .map { it.toDomain() }
    }
}
