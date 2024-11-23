package com.yourssu.openssupot.campus.domain.domain.startupportal

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "startupPortalClient", url = "https://startup.ssu.ac.kr")
interface StartupPortalClient {

    @GetMapping("/api/rental/time/list/{spaceId}?bookingDate=2024-11-25")
    fun getRentalTimes(
        @PathVariable("spaceId") spaceId: Long,
        @RequestParam("bookingDate") bookingDate: String,
    ): RentalTimesResponse
}

data class RentalTimesResponse(
    val message: String?,
    val data: List<RentalTimeItem>,
    val code: Int
)

data class RentalTimeItem(
    val rownum: Int,
    val rentalTimeId: Int,
    val rentalItemId: Int,
    val rentalTime: String,
    val possibleYn: String
)
