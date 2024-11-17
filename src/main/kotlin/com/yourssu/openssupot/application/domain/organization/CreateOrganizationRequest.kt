package com.yourssu.openssupot.application.domain.organization

import com.yourssu.openssupot.domain.domain.organization.CreateOrganizationCommand
import jakarta.validation.constraints.NotEmpty
import org.springframework.web.multipart.MultipartFile

data class CreateOrganizationRequest(

    @NotEmpty(message = "이메일이 입력되지 않았습니다.")
    val email: String,

    @NotEmpty(message = "비밀번호가 입력되지 않았습니다.")
    val password: String,

    @NotEmpty(message = "단체 이름이 입력되지 않았습니다.")
    val name: String,

    val description: String? = null,

    @NotEmpty(message = "예약 비밀번호가 입력되지 않았습니다.")
    val reservationPassword: String,
) {
    fun toCommand(logoImage: MultipartFile?): CreateOrganizationCommand {
        return CreateOrganizationCommand(
            email = email,
            rawPassword = password,
            name = name,
            logoImage = logoImage,
            description = description,
            rawReservationPassword = reservationPassword,
        )
    }
}
