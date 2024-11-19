package com.yourssu.openssupot.application.domain.reservation

import com.yourssu.openssupot.domain.domain.reservation.ReservationService
import jakarta.validation.Valid
import java.net.URI
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReservationController(
    private val reservationService: ReservationService,
) {

    @PostMapping("/spaces/{spaceId}/reservations")
    fun create(
        @PathVariable spaceId: Long,
        @RequestBody @Valid request: CreateReservationRequest,
    ): ResponseEntity<Unit> {
        val reservationId = reservationService.create(request.toCommand(spaceId))

        return ResponseEntity.created(URI.create("/spaces/$spaceId/reservations/$reservationId")).build()
    }
}
