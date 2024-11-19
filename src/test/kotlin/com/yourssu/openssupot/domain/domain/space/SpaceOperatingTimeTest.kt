package com.yourssu.openssupot.domain.domain.space

import com.yourssu.openssupot.domain.domain.reservation.ReservationTime
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("NonAsciiCharacters")
class SpaceOperatingTimeTest {

    @ParameterizedTest
    @MethodSource("generateValidReservationTimeCases")
    fun `예약 가능한 시간이면 ture를 반환한다`(spaceOperatingTime: SpaceOperatingTime, reservationTime: ReservationTime) {
        // then
        assertThat(spaceOperatingTime.isAvailableTime(reservationTime)).isTrue()
    }

    @ParameterizedTest
    @MethodSource("generateInvalidReservationTimeCases")
    fun `예약 가능한 시간이 아니면 false를 반환한다`(spaceOperatingTime: SpaceOperatingTime, reservationTime: ReservationTime) {
        // then
        assertThat(spaceOperatingTime.isAvailableTime(reservationTime)).isFalse()
    }

    companion object {
        @JvmStatic
        fun generateValidReservationTimeCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 10, 0),
                        LocalDateTime.of(2021, 10, 1, 14, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 14, 0),
                        LocalDateTime.of(2021, 10, 1, 18, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(22, 0), LocalTime.of(6, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 22, 0),
                        LocalDateTime.of(2021, 10, 1, 23, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(22, 0), LocalTime.of(6, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 23, 0),
                        LocalDateTime.of(2021, 10, 2, 3, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(22, 0), LocalTime.of(6, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 2, 3, 0),
                        LocalDateTime.of(2021, 10, 2, 6, 0)
                    )
                ),
            )
        }

        @JvmStatic
        fun generateInvalidReservationTimeCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 17, 0),
                        LocalDateTime.of(2021, 10, 1, 19, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 19, 0),
                        LocalDateTime.of(2021, 10, 1, 22, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 22, 0),
                        LocalDateTime.of(2021, 10, 2, 5, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(10, 0), LocalTime.of(18, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 2, 5, 0),
                        LocalDateTime.of(2021, 10, 2, 10, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(22, 0), LocalTime.of(6, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 6, 0),
                        LocalDateTime.of(2021, 10, 1, 15, 0)
                    )
                ),
                Arguments.arguments(
                    SpaceOperatingTime(LocalTime.of(22, 0), LocalTime.of(6, 0)),
                    ReservationTime(
                        LocalDateTime.of(2021, 10, 1, 15, 0),
                        LocalDateTime.of(2021, 10, 1, 22, 0)
                    )
                ),
            )
        }
    }
}
