package com.yourssu.openssupot.campus.application.domain

import com.yourssu.openssupot.campus.domain.domain.CampusSpaceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Campus Space", description = "교낵 공간 API")
class CampusSpaceController(
    private val campusSpaceService: CampusSpaceService,
) {

    @Operation(
        summary = "교내 공간 목록 조회",
    )
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
