package com.yourssu.openssupot.storage.domain.reservation

import org.springframework.data.jpa.repository.JpaRepository

interface JpaReservationRepository : JpaRepository<ReservationEntity, Long> {
}
