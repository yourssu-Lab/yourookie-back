package com.yourssu.openssupot.storage.domain.reservation

import com.yourssu.openssupot.domain.domain.reservation.Reservation
import com.yourssu.openssupot.domain.domain.reservation.ReservationRepository
import org.springframework.stereotype.Repository

@Repository
class ReservationRepositoryImpl(
    private val jpaReservationRepository: JpaReservationRepository,
) : ReservationRepository {

    override fun save(reservation: Reservation): Reservation {
        return jpaReservationRepository.save(ReservationEntity.from(reservation)).toDomain()
    }
}
