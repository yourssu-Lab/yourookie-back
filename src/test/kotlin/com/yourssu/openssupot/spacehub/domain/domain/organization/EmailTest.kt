package com.yourssu.openssupot.spacehub.domain.domain.organization

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@Suppress("NonAsciiCharacters")
class EmailTest {

    @Test
    fun `빈 이메일 주소를 생성하면 예외가 발생한다`() {
        // given
        val emptyEmail = "  "

        // when & then
        assertThatThrownBy { Email(emptyEmail) }
            .isInstanceOf(InvalidEmailException::class.java)
            .hasMessage("email 주소가 빈 값입니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["invalid-email", "user@domain", "user@domain.", "user@domain@domain.com"])
    fun `유효하지 않은 이메일 형식이면 예외가 발생한다`(invalidEmail: String) {
        // when & then
        assertThatThrownBy { Email(invalidEmail) }
            .isInstanceOf(InvalidEmailException::class.java)
            .hasMessage("유효하지 않은 email 형식입니다.")
    }

    @Test
    fun `유효한 이메일 주소라면 객체가 정상적으로 생성된다`() {
        // given
        val validEmail = "encho.urssu@soongsil.ac.kr"

        // when
        val email = Email(validEmail)

        // then
        assertThat(email.emailAddress).isEqualTo(validEmail)
    }
}
