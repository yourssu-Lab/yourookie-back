package com.yourssu.openssupot.domain.domain.reservation

import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

@Suppress("NonAsciiCharacters")
class ReservationTimeTest {

    @Test
    fun `예약 시작 시간이 종료 시간보다 늦으면 예외가 발생한다`() {
        // given
        val startDateTime = LocalDateTime.of(2021, 10, 1, 10, 0)
        val endDateTime = LocalDateTime.of(2021, 10, 1, 9, 0)

        // when
        assertThatThrownBy { ReservationTime(startDateTime, endDateTime) }
            .isInstanceOf(InvalidReservationTimeException::class.java)
            .hasMessage("예약 시작 시간이 종료 시간보다 늦습니다.")
    }

    @Test
    fun `예약 시작 시간과 종료 시간이 하루(24시간) 이상 차이이면 예외가 발생한다`() {
        // given
        val startDateTime = LocalDateTime.of(2021, 10, 1, 10, 0)
        val endDateTime = LocalDateTime.of(2021, 10, 2, 10, 0)

        // when
        assertThatThrownBy { ReservationTime(startDateTime, endDateTime) }
            .isInstanceOf(InvalidReservationTimeException::class.java)
            .hasMessage("하루(24시간) 이상의 예약은 불가능합니다.")
    }

    @Test
    fun `유효한 예약 시작 시간과 종료 시간이라면 객체가 정상적으로 생성된다`() {
        // given
        val startDateTime = LocalDateTime.of(2021, 10, 1, 10, 0)
        val endDateTime = LocalDateTime.of(2021, 10, 1, 12, 0)

        // when & then
        assertDoesNotThrow { ReservationTime(startDateTime, endDateTime) }
    }
}
