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

    fun getEncryptedReservationPassword(): String {
        return organization.encryptedReservationPassword
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Space

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Space(id=$id, organization=$organization, name='$name', location='$location', spaceImageUrl=$spaceImageUrl, operatingTime=$operatingTime, capacity=$capacity)"
    }
}
