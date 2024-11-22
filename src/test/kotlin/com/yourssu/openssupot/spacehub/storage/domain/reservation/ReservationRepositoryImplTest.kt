package com.yourssu.openssupot.spacehub.storage.domain.reservation

import com.yourssu.openssupot.spacehub.domain.domain.organization.Email
import com.yourssu.openssupot.spacehub.domain.domain.organization.Organization
import com.yourssu.openssupot.spacehub.domain.domain.organization.OrganizationName
import com.yourssu.openssupot.spacehub.domain.domain.organization.OrganizationRepository
import com.yourssu.openssupot.spacehub.domain.domain.reservation.Reservation
import com.yourssu.openssupot.spacehub.domain.domain.reservation.ReservationRepository
import com.yourssu.openssupot.spacehub.domain.domain.reservation.ReservationTime
import com.yourssu.openssupot.spacehub.domain.domain.space.Capacity
import com.yourssu.openssupot.spacehub.domain.domain.space.Space
import com.yourssu.openssupot.spacehub.domain.domain.space.SpaceOperatingTime
import com.yourssu.openssupot.spacehub.domain.domain.space.SpaceRepository
import com.yourssu.openssupot.spacehub.storage.domain.organization.JpaOrganizationRepository
import com.yourssu.openssupot.spacehub.storage.domain.organization.OrganizationRepositoryImpl
import com.yourssu.openssupot.spacehub.storage.domain.space.JpaSpaceRepository
import com.yourssu.openssupot.spacehub.storage.domain.space.SpaceRepositoryImpl
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@Suppress("NonAsciiCharacters")
class ReservationRepositoryImplTest {

    @Autowired
    private lateinit var jpaOrganizationRepository: JpaOrganizationRepository

    @Autowired
    private lateinit var jpaSpaceRepository: JpaSpaceRepository

    @Autowired
    private lateinit var jpaReservationRepository: JpaReservationRepository

    private lateinit var organizationRepository: OrganizationRepository
    private lateinit var spaceRepository: SpaceRepository
    private lateinit var reservationRepository: ReservationRepository

    @BeforeEach
    fun setup() {
        organizationRepository = OrganizationRepositoryImpl(jpaOrganizationRepository)
        spaceRepository = SpaceRepositoryImpl(jpaSpaceRepository)
        reservationRepository = ReservationRepositoryImpl(jpaReservationRepository)
    }

    @ParameterizedTest
    @CsvSource(
        "2024-11-20T10:00, 2024-11-20T12:00",
        "2024-11-20T10:00, 2024-11-20T14:00",
        "2024-11-20T10:00, 2024-11-20T18:00",
        "2024-11-20T11:30, 2024-11-20T12:30",
        "2024-11-20T12:00, 2024-11-20T14:00",
        "2024-11-20T14:00, 2024-11-20T16:00",
        "2024-11-20T14:00, 2024-11-20T18:00",
        "2024-11-20T15:30, 2024-11-20T16:30",
        "2024-11-20T16:00, 2024-11-20T18:00",
    )
    fun `주어진 공간에 주어진 시간 범위와 겹치는 예약이 존재하면 true를 반환한다`(
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ) {
        // given
        val organization = saveOrganization("email@naver.com")
        val space = saveSpace(organization, LocalTime.of(10, 0), LocalTime.of(18, 0))
        saveReservation(
            space,
            LocalDateTime.of(2024, 11, 20, 11, 0),
            LocalDateTime.of(2024, 11, 20, 13, 0),
        )
        saveReservation(
            space,
            LocalDateTime.of(2024, 11, 20, 15, 0),
            LocalDateTime.of(2024, 11, 20, 17, 0),
        )

        // when
        val result = reservationRepository.existsBySpaceIdAndDateTimeRange(space.id!!, startDateTime, endDateTime)

        // then
        assertThat(result).isTrue()
    }

    @ParameterizedTest
    @CsvSource(
        "2024-11-20T10:00, 2024-11-20T11:00",
        "2024-11-20T13:00, 2024-11-20T14:00",
        "2024-11-20T14:00, 2024-11-20T15:00",
        "2024-11-20T17:00, 2024-11-20T18:00",
    )
    fun `주어진 공간에 주어진 시간 범위와 겹치는 예약이 존재하지 않으면 false를 반환한다`(
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ) {
        // given
        val organization = saveOrganization("email@naver.com")
        val space = saveSpace(organization, LocalTime.of(10, 0), LocalTime.of(18, 0))
        saveReservation(
            space,
            LocalDateTime.of(2024, 11, 20, 11, 0),
            LocalDateTime.of(2024, 11, 20, 13, 0),
        )
        saveReservation(
            space,
            LocalDateTime.of(2024, 11, 20, 15, 0),
            LocalDateTime.of(2024, 11, 20, 17, 0),
        )

        // when
        val result = reservationRepository.existsBySpaceIdAndDateTimeRange(space.id!!, startDateTime, endDateTime)

        // then
        assertThat(result).isFalse()
    }

    @Test
    fun `주어진 공간에 주어진 시간 범위와 겹치는 예약 목록을 반환한다`() {
        // given
        val organization = saveOrganization("email@naver.com")
        val space1 = saveSpace(organization, LocalTime.of(22, 0), LocalTime.of(6, 0))
        val space2 = saveSpace(organization, LocalTime.of(22, 0), LocalTime.of(6, 0))
        val targetSpace = space1
        val anotherSpace = space2
        val targetDate = LocalDate.of(2024, 11, 20)
        val startOfDay: LocalDateTime = targetDate.atStartOfDay()
        val endOfDay: LocalDateTime = targetDate.atTime(LocalTime.MAX)

        val anotherSpaceReservation = saveReservation(
            anotherSpace,
            LocalDateTime.of(2024, 11, 20, 22, 0),
            LocalDateTime.of(2024, 11, 20, 23, 0),
        )
        val notInTargetDate1 = saveReservation(
            targetSpace,
            LocalDateTime.of(2024, 11, 19, 1, 0),
            LocalDateTime.of(2024, 11, 19, 3, 0),
        )
        val include1 = saveReservation(
            targetSpace,
            LocalDateTime.of(2024, 11, 19, 22, 0),
            LocalDateTime.of(2024, 11, 20, 1, 0),
        )
        val include2 = saveReservation(
            targetSpace,
            LocalDateTime.of(2024, 11, 20, 1, 0),
            LocalDateTime.of(2024, 11, 20, 3, 0),
        )
        val include3 = saveReservation(
            targetSpace,
            LocalDateTime.of(2024, 11, 20, 22, 0),
            LocalDateTime.of(2024, 11, 21, 1, 0),
        )
        val notInTargetDate2 = saveReservation(
            targetSpace,
            LocalDateTime.of(2024, 11, 21, 1, 0),
            LocalDateTime.of(2024, 11, 21, 3, 0),
        )

        // when
        val result: List<Reservation> = reservationRepository.findAllBySpaceIdAndDateTimeRange(
            space1.id!!,
            startOfDay,
            endOfDay
        )

        // then
        assertThat(result).containsExactlyInAnyOrder(include1, include2, include3)
    }

    private fun saveSpace(organization: Organization, openTime: LocalTime, closeTime: LocalTime): Space =
        spaceRepository.save(
            Space(
                id = null,
                organization = organization,
                name = "name",
                location = "location",
                spaceImageUrl = "https://example.com/space.png",
                operatingTime = SpaceOperatingTime(openTime, closeTime),
                capacity = Capacity(10),
            )
        )

    fun saveOrganization(email: String): Organization = organizationRepository.save(
        Organization(
            id = null,
            email = Email(email),
            encryptedPassword = "\$2a\$10\$SG1qTzy5vDOLYaPQ5ws/aA+K1mG2ekX+IuE8EXk/xhF0RQoNlXsXl",
            name = OrganizationName("조직"),
            logoImageUrl = "https://example.com/logo.png",
            description = "조직입니다.",
            encryptedReservationPassword = "\$2a\$10\$SG1qTzy5vDOLYaPQ5ws/aA+K1mG2ekX+IuE8EXk/xhF0RQoNlXsXl",
        )
    )

    private fun saveReservation(space: Space, startDateTime: LocalDateTime, endDateTime: LocalDateTime): Reservation =
        reservationRepository.save(
            Reservation(
                id = null,
                space = space,
                bookerName = "booker",
                reservationTime = ReservationTime(startDateTime, endDateTime),
                encryptedPersonalPassword = "\$2a\$10\$SG1qTzy5vDOLYaPQ5ws/aA+K1mG2ekX+IuE8EXk/xhF0RQoNlXsXl",
            )
        )
}
