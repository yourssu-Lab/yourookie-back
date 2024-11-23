package com.yourssu.openssupot.campus.application.domain

import com.yourssu.openssupot.campus.domain.domain.CampusSpaceService
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CampusSpaceController(
    private val campusSpaceService: CampusSpaceService,
) {

    @GetMapping("/meetingrooms")
    fun readAll(
        @RequestParam startDateTime: LocalDateTime,
        @RequestParam endDateTime: LocalDateTime,
    ): List<ReadMeetingRoomResponse> {

        return campusSpaceService.readAllByTimeRange(startDateTime, endDateTime)
            .map {
                ReadMeetingRoomResponse.from(it)
            }
    }
}
