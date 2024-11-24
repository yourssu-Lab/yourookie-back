package com.yourssu.openssupot.campus.domain.domain.oasis

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "oasisClient", url = "https://oasis.ssu.ac.kr")
interface OasisClient {

    @PostMapping("/pyxis-api/api/login")
    fun login(request: LoginRequest): LoginResponse

    @GetMapping("/pyxis-api/1/api/rooms")
    fun getSeminarRooms(
        @RequestHeader("pyxis-auth-token") accessToken: String,
        @RequestParam("roomTypeId") roomTypeId: Long,
        @RequestParam("smufMethodCode") smufMethodCode: String = "PC",
        @RequestParam("hopeDate") hopeDate: String,
    ): SeminarRoomsResponse
}

data class LoginRequest(
    val isFamilyLogin: Boolean = false,
    val isMobile: Boolean = false,
    val loginId: String,
    val password: String,
)

data class LoginResponse(
    val success: Boolean,
    val data: DateItem?,
)

data class DateItem(
    val accessToken: String,
)

data class SeminarRoomsResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: DataResponseItem
)

data class DataResponseItem(
    val totalCount: Int,
    val list: List<RoomResponseItem>
)

data class RoomResponseItem(
    val id: Int,
    val name: String,
    val roomType: RoomTypeResponseItem,
    val floor: FloorResponseItem,
    val minQuota: Int,
    val maxQuota: Int,
    val quota: String,
    val hopeDate: String,
    val isChargeable: Boolean,
    val timeLine: List<TimeSlotResponseItem>
)

data class RoomTypeResponseItem(
    val id: Int,
    val name: String,
    val sortOrder: Int
)

data class FloorResponseItem(
    val value: Int,
    val label: String
)

data class TimeSlotResponseItem(
    val hour: Int,
    val minutes: List<MinuteSlotResponseItem>
)

data class MinuteSlotResponseItem(
    @JsonProperty("class")
    val status: String,
    val selectable: Boolean? = null
)
