package com.yourssu.openssupot.campus.domain.domain.oasis

class SeminarRoom(
    val id: Int,
    val name: String,
    val roomType: RoomType,
    val capacity: String,
    val isChargeable: Boolean,
    val timeLine: List<TimeSlot>
) {

    companion object {
        fun from(roomResponseItem: RoomResponseItem, roomType: RoomType): SeminarRoom {
            return SeminarRoom(
                id = roomResponseItem.id,
                name = roomResponseItem.name,
                roomType = roomType,
                capacity = roomResponseItem.quota,
                isChargeable = roomResponseItem.isChargeable,
                timeLine = roomResponseItem.timeLine.map { TimeSlot.from(it) }
            )
        }
    }
}

enum class RoomType(val id: Int, val location: String, val operatingTime: String) {
    SEMINAR_ROOM(1, "중앙도서관 1층", "10:00 ~ 21:00 (학기-평일)\n09:00 ~ 15:00 (학기-토요일)\n10:00~19:30(방학-평일)"),
    OPEN_SEMINAR_ROOM(5, "중앙도서관 숭실스퀘어(1F)", "06:00 ~ 23:30 (학기)\n06:00 ~ 23:30 (방학)")
}

class TimeSlot(
    val hour: Int,
    val minutes: List<MinuteSlot>
) {
    companion object {
        fun from(timeSlotResponseItem: TimeSlotResponseItem): TimeSlot {
            return TimeSlot(
                hour = timeSlotResponseItem.hour,
                minutes = timeSlotResponseItem.minutes.map { MinuteSlot.from(it) }
            )
        }
    }
}

class MinuteSlot(

    val status: String,
    val selectable: Boolean? = null
) {
    companion object {
        fun from(minuteSlotResponseItem: MinuteSlotResponseItem): MinuteSlot {
            return MinuteSlot(
                status = minuteSlotResponseItem.status,
                selectable = minuteSlotResponseItem.selectable,
            )
        }
    }
}
