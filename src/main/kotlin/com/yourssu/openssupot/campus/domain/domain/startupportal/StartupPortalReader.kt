package com.yourssu.openssupot.campus.domain.domain.startupportal

import java.time.LocalDate
import org.springframework.stereotype.Component

@Component
class StartupPortalReader(
    private val startupPortalClient: StartupPortalClient,
) {

    fun getAllByDate(date: LocalDate): List<StartupSpace> {
        val startupSpaces = mutableListOf<StartupSpace>()

        for (spaceType in SpaceType.entries) {
            val rentalTimesResponse = startupPortalClient.getRentalTimes(
                spaceId = spaceType.id.toLong(),
                bookingDate = date.toString(),
            )
            startupSpaces.add(StartupSpace.from(rentalTimesResponse))
        }

        return startupSpaces
    }
}
