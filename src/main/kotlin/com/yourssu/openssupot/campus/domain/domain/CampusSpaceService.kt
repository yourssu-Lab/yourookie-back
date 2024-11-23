package com.yourssu.openssupot.campus.domain.domain

import com.yourssu.openssupot.campus.domain.domain.oasis.OasisReader
import com.yourssu.openssupot.campus.domain.domain.oasis.SeminarRoom
import com.yourssu.openssupot.campus.domain.domain.startupportal.StartupPortalReader
import com.yourssu.openssupot.campus.domain.domain.startupportal.StartupSpace
import com.yourssu.openssupot.spacehub.domain.domain.file.FileProcessor
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class CampusSpaceService(
    private val oasisReader: OasisReader,
    private val startupPortalReader: StartupPortalReader,
    private val fileProcessor: FileProcessor,  // TODO: 기본 이미지 처리 리팩토링 필요
) {

    fun readAllByTimeRange(startDateTime: LocalDateTime, endDateTime: LocalDateTime): List<ReadMeetingRoomDto> {
        val oasisMeetingRoomDtos: List<ReadMeetingRoomDto> = readFromOasis(startDateTime, endDateTime)
        val startupPortalMeetingRoomDtos: List<ReadMeetingRoomDto> = readFromStartupPortal(startDateTime, endDateTime)

        return oasisMeetingRoomDtos + startupPortalMeetingRoomDtos
    }

    private fun readFromOasis(
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): List<ReadMeetingRoomDto> {
        val availableSeminarRooms: List<SeminarRoom> = oasisReader.getAllByDate(startDateTime.toLocalDate())
            .filter {
                it.isAvailable(
                    startDateTime.toLocalTime(),
                    endDateTime.toLocalTime()
                )
            }

        return availableSeminarRooms.map {
            ReadMeetingRoomDto.from(
                it,
                fileProcessor.getDefaultOrganizationImageUrl(),
                getOasisReservationUrl(it.id, startDateTime.toLocalDate())
            )
        }
    }

    private fun getOasisReservationUrl(seminarRoomId: Int, hopeDay: LocalDate): String {
        return "https://oasis.ssu.ac.kr/library-services/smuf/rooms/$seminarRoomId/$hopeDay"
    }

    private fun readFromStartupPortal(
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime
    ): List<ReadMeetingRoomDto> {
        val availableStartupSpaces: List<StartupSpace> = startupPortalReader.getAllByDate(startDateTime.toLocalDate())
            .filter {
                it.isAvailable(
                    startDateTime.toLocalTime(),
                    endDateTime.toLocalTime()
                )
            }

        return availableStartupSpaces.map {
            ReadMeetingRoomDto.from(it, getStartupSpaceReservationUrl())
        }
    }

    private fun getStartupSpaceReservationUrl(): String {
        return "https://startup.ssu.ac.kr/foundation/reservation/space"
    }
}
