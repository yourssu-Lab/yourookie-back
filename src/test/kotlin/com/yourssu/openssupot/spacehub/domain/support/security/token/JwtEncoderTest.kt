package com.yourssu.openssupot.spacehub.domain.support.security.token

import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class JwtEncoderTest {

    private val jwtProperties = JwtProperties(
        "ThisIsLocalAccessKeyThisIsLocalAccessKey",
        "ThisIsLocalRefreshKeyThisIsLocalRefreshKey",
        1L,
        336L
    )

    @Test
    fun `토큰을 인코딩한다`() {
        // given
        val jwtEncoder = JwtEncoder(jwtProperties)
        val privateClaims = mapOf("userId" to 1L)

        // when
        val actual = jwtEncoder.encode(LocalDateTime.now(), TokenType.ACCESS, privateClaims)

        // then
        assertThat(actual).contains("Bearer ")
    }
}
