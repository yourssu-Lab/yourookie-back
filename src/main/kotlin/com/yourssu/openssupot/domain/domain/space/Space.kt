package com.yourssu.openssupot.domain.domain.space

import com.yourssu.openssupot.domain.domain.organization.Organization
import com.yourssu.openssupot.domain.domain.reservation.ReservationTime
import java.time.LocalTime

class Space(
    val id: Long? = null,
    val organization: Organization,
    val name: String,
    val location: String,
    val spaceImageUrl: String? = null,
    val operatingTime: SpaceOperatingTime,
    val capacity: Capacity,
) {

    fun canReserve(reservationTime: ReservationTime): Boolean {
        return operatingTime.isAvailableTime(reservationTime)
    }

    fun getOpeningTime(): LocalTime {
        return operatingTime.openingTime
    }

    fun getClosingTime(): LocalTime {
        return operatingTime.closingTime
    }

    fun getCapacityValue(): Int {
        return capacity.value
    }
}
