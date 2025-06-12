package com.yourssu.openssupot.spacehub.application.domain.reservation

import com.yourssu.openssupot.spacehub.domain.domain.reservation.ReservationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.net.URI
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Reservation", description = "공간 예약 API")
class ReservationController(
    private val reservationService: ReservationService,
) {

    @Operation(
        summary = "공간 예약",
    )
    @PostMapping("/spaces/{spaceId}/reservations")
    fun create(
        @PathVariable spaceId: Long,
        @RequestBody @Valid request: CreateReservationRequest,
    ): ResponseEntity<Unit> {
        val reservationId = reservationService.create(request.toCommand(spaceId))

        return ResponseEntity.created(URI.create("/spaces/$spaceId/reservations/$reservationId")).build()
    }

    @Operation(
        summary = "공간 예약 목록 조회",
    )
    @GetMapping("/spaces/{spaceId}/reservations")
    fun readAll(
        @PathVariable spaceId: Long,
        @RequestParam(required = false) date: LocalDate?,
        @RequestParam(required = false) time: LocalDateTime?
    ): ResponseEntity<List<ReadReservationResponse>> {
        val result = when {
            date != null -> reservationService.readAllByDate(spaceId, date)
            time != null -> reservationService.readAllAfterTime(spaceId, time)
            else -> throw IllegalArgumentException("Either date or time must be provided")
        }

        val responses: List<ReadReservationResponse> = result.reservationDtos.map { ReadReservationResponse.from(it) }

        return ResponseEntity.ok(responses)
    }

    @Operation(
        summary = "공간 예약 취소",
    )
    @DeleteMapping("/reservations/{reservationId}")
    fun delete(
        @PathVariable reservationId: Long,
        @RequestBody @Valid request: DeleteReservationRequest,
    ): ResponseEntity<Unit> {
        reservationService.delete(reservationId, request.personalPassword)

        return ResponseEntity.noContent().build()
    }
}
