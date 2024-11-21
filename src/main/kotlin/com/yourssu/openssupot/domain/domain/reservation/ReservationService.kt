package com.yourssu.openssupot.domain.domain.reservation

import com.yourssu.openssupot.domain.domain.authentication.PasswordNotMatchException
import com.yourssu.openssupot.domain.domain.password.PasswordValidator
import com.yourssu.openssupot.domain.domain.space.Space
import com.yourssu.openssupot.domain.domain.space.SpaceReader
import com.yourssu.openssupot.domain.support.security.password.PasswordEncoder
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val spaceReader: SpaceReader,
    private val passwordEncoder: PasswordEncoder,
    private val reservationReader: ReservationReader,
    private val reservationWriter: ReservationWriter,
) {

    fun create(command: CreateReservationCommand): Long {
        val space: Space = spaceReader.getById(command.spaceId)

        if (!passwordEncoder.matches(command.password, space.getEncryptedReservationPassword())) {
            throw PasswordNotMatchException("예약 비밀번호가 일치하지 않습니다.")
        }

        val reservationTime = ReservationTime(command.startDateTime, command.endDateTime)
        if (!space.canReserve(reservationTime)) {
            throw InvalidReservationException("공간 사용 가능 시간이 아닙니다.")
        }

        PasswordValidator.validatePersonalPassword(command.rawPersonalPassword)

        val encryptedPersonalPassword: String = passwordEncoder.encode(command.rawPersonalPassword)
        val reservation = Reservation(
            space = space,
            bookerName = command.bookerName,
            reservationTime = reservationTime,
            encryptedPersonalPassword = encryptedPersonalPassword,
        )

        if (reservationReader.isTimeConflict(reservation)) {
            throw ReservationConflictException("이미 예약된 시간입니다.")
        }

        val savedReservation: Reservation = reservationWriter.write(reservation)

        return savedReservation.id!!
    }

    fun readAllByDate(spaceId: Long, date: LocalDate): ReadReservationsResult {
        val space: Space = spaceReader.getById(spaceId)
        val reservations: List<Reservation> = reservationReader.getAllBySpaceAndDate(space, date)

        return ReadReservationsResult.from(reservations)
    }
}
