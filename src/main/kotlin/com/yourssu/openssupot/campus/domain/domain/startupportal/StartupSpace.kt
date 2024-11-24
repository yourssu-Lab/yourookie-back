package com.yourssu.openssupot.campus.domain.domain.startupportal

import java.time.LocalTime

class StartupSpace(
    val id: Int,
    val spaceType: SpaceType,
    val rentals: List<RentalStatus>,
) {

    fun isAvailable(startTime: LocalTime, endTime: LocalTime): Boolean {
        for (rental in rentals) {
            val slotStartTime = rental.rentalTime
            val slotEndTime = rental.rentalTime.plusHours(1L)
            if (slotEndTime <= startTime || endTime <= slotStartTime) {
                continue
            }

            if (!rental.possible) {
                return false
            }
        }

        val lastTime = rentals[rentals.size - 1].rentalTime.plusHours(1L)

        return endTime <= lastTime
    }

    companion object {
        fun from(
            rentalTimesResponse: RentalTimesResponse
        ): StartupSpace {
            val spaceId = rentalTimesResponse.data[0].rentalItemId
            return StartupSpace(
                id = spaceId,
                spaceType = SpaceType.of(spaceId),
                rentals = rentalTimesResponse.data.map { RentalStatus.from(it) }
            )
        }
    }
}

class RentalStatus(
    val rentalTime: LocalTime,
    val possible: Boolean,
) {

    companion object {
        fun from(rentalTimeItem: RentalTimeItem): RentalStatus {
            return RentalStatus(
                rentalTime = LocalTime.parse(rentalTimeItem.rentalTime),
                possible = rentalTimeItem.possibleYn == "Y"
            )
        }
    }
}

enum class SpaceType(
    val id: Int,
    val spaceName: String,
    val spaceImageUrl: String,
    val location: String,
    val operatingTime: String,
    val capacity: String,
) {

    TECH_STATION_B108(
        22,
        "테크스테이션 B108",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F30ae0cd7-7276-4825-8ec3-1b62c0a136c4.png&w=1920&q=75",
        "테크스테이션 지하 108호 회의실",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 12",
    ),
    TECH_STATION_B107(
        23,
        "테크스테이션 B107",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F3861ede4-a9dc-4053-8ff9-fa1c3f4efef1.png&w=1920&q=75",
        "테크스테이션 지하 107호 회의실",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 8",
    ),
    STATION_365_203(
        30,
        "365스테이션 203호 회의실",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F9ad7a33f-83ca-495a-91af-5d435ef7e5b5.png&w=1080&q=75",
        "365스테이션 203호 회의실",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 6",
    ),
    CHALLENGE_STATION(
        29,
        "챌린지스테이션 2층 회의실",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F9db6baa6-a457-422c-bd65-2114c19e62a7.png&w=1080&q=75",
        "챌린지스테이션 2층 회의실",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 12",
    ),
    VENTURE_STUDIO_PASSION(
        25,
        "프로젝트룸 Passion",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F14e18aa5-583d-406f-889c-00741b57ae06.png&w=1200&q=75",
        "벤처중소기업센터 2층 벤처스튜디오",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 7",
    ),
    VENTURE_STUDIO_UNIQUE(
        26,
        "프로젝트룸 Unique",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2Fbc082ab9-5c06-484d-8f7e-e064b4046ee2.png&w=1200&q=75",
        "벤처중소기업센터 2층 벤처스튜디오",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 7",
    ),
    VENTURE_STUDIO_MAKE(
        27,
        "프로젝트룸 Make",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2Fe49168a5-6598-4690-9f2a-859ad85364b2.png&w=1200&q=75",
        "벤처중소기업센터 2층 벤처스튜디오",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 7",
    ),
    VENTURE_STUDIO_PIONEER(
        28,
        "프로젝트룸 Pioneer",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F43101f18-4db5-4897-bb17-3a6c6dc244ce.png&w=1200&q=75",
        "벤처중소기업센터 2층 벤처스튜디오",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 7",
    ),
    CHANGSHIN_HALL_309(
        24,
        "창신관 309호",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F4b186a60-c770-4ffb-ac2e-c6aa2d7ff831.png&w=1200&q=75",
        "창신관 309호",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 10",
    ),
    CHANGUI_HALL_B103(
        33,
        "창의관 B103호 실습실",
        "https://startup.ssu.ac.kr/_next/image?url=https%3A%2F%2Fstartup.ssu.ac.kr%2Fapi%2Fresource%2FRENTAL_ITEM_IMG%2F2023%2F06%2F563c747d-3290-42ff-95cc-0783b7f25d6f.png&w=1200&q=75",
        "창의관 B103호 실습실",
        "09:00 ~ 17:00 (평일)",
        "1 ~ 7",
    );

    companion object {
        fun of(id: Int): SpaceType {
            return entries.find { it.id == id }
                ?: throw IllegalArgumentException("Invalid SpaceType id")
        }
    }
}
