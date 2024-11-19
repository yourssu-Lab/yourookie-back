package com.yourssu.openssupot.domain.domain.reservation

import com.yourssu.openssupot.domain.domain.space.Space
import java.time.LocalDateTime

class Reservation(
    val id: Long? = null,
    val space: Space,
    val bookerName: String,
    val reservationTime: ReservationTime,
) {
    init {
        if (!space.canReserve(reservationTime)) {
            throw InvalidReservationException("공간 사용 가능 시간이 아닙니다.")
        }
    }

    fun getStartDateTime(): LocalDateTime {
        return reservationTime.startDateTime
    }

    fun getEndDateTime(): LocalDateTime {
        return reservationTime.endDateTime
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reservation

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Reservation(id=$id, space=$space, bookerName='$bookerName', reservationTime=$reservationTime)"
    }
}
