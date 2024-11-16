package com.yourssu.openssupot.domain.support.security.password

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class EncryptPasswordEncoderTest {

    @Test
    fun `평문과 암호화한 문자열은 다르다`() {
        // given
        val encryptPasswordEncoder = EncryptPasswordEncoder()
        val plainPassword = "plainPassword"

        // when
        val encodedPassword = encryptPasswordEncoder.encode(plainPassword)

        // then
        assertThat(encodedPassword).isNotEqualTo(plainPassword)
    }

    @Test
    fun `암호화할 비밀번호가 비어있다면 예외가 발생한다`() {
        // given
        val encryptPasswordEncoder = EncryptPasswordEncoder()
        val blankPlainPassword = " "

        // then
        assertThatThrownBy { encryptPasswordEncoder.encode(blankPlainPassword) }
            .isInstanceOf(PasswordEncodingFailureException::class.java)
            .hasMessage("비밀번호가 빈 값입니다.")
    }
}
