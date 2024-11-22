package com.yourssu.openssupot.spacehub.domain.support.security.token

import io.jsonwebtoken.Claims
import java.time.LocalDateTime
import java.time.ZoneId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class JwtDecoderTest {

    private val jwtProperties: JwtProperties = JwtProperties(
        "ThisIsLocalAccessKeyThisIsLocalAccessKey",
        "ThisIsLocalRefreshKeyThisIsLocalRefreshKey",
        1L,
        336L
    )

    @Test
    fun `토큰의 타입이 유효하지 않다면 예외가 발생한다`() {
        // given
        val jwtDecoder = JwtDecoder(jwtProperties)
        val invalidTypeToken = "Basic12 abcde"

        // when & then
        assertThatThrownBy { jwtDecoder.decode(TokenType.ACCESS, invalidTypeToken) }
            .isInstanceOf(InvalidTokenException::class.java)
            .hasMessage("Bearer 타입이 아닙니다.")
    }

    @Test
    fun `토큰이 유효하지 않다면 null을 반환한다`() {
        // given
        val jwtDecoder = JwtDecoder(jwtProperties)
        val invalidToken = "Bearer abcde"

        // when
        val actual: Claims? = jwtDecoder.decode(TokenType.ACCESS, invalidToken)

        // then
        assertThat(actual).isNull()
    }

    @Test
    fun `유효한 토큰이면 디코딩한 값을 반환한다`() {
        // given
        val tokenEncoder: TokenEncoder = JwtEncoder(jwtProperties)
        val jwtDecoder = JwtDecoder(jwtProperties)
        val keyName = "userId"
        val userId = 1L

        val privateClaims = mapOf(keyName to userId)
        val validToken = tokenEncoder.encode(
            LocalDateTime.now(ZoneId.of("Asia/Seoul")),
            TokenType.ACCESS,
            privateClaims
        )

        // when
        val actual: Claims? = jwtDecoder.decode(TokenType.ACCESS, validToken)

        // then
        assertThat((actual!![keyName] as Number).toLong()).isEqualTo(userId)
    }
}
