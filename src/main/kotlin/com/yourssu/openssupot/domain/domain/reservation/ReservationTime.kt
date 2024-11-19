package com.yourssu.openssupot.domain.domain.reservation

import java.time.LocalDateTime

class ReservationTime(
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
) {

    init {
        if (startDateTime.isAfter(endDateTime)) {
            throw InvalidReservationTimeException("예약 시작 시간이 종료 시간보다 늦습니다.")
        }
        if (startDateTime.plusDays(1L) <= endDateTime) {
            throw InvalidReservationTimeException("하루(24시간) 이상의 예약은 불가능합니다.")
        }
    }
}
