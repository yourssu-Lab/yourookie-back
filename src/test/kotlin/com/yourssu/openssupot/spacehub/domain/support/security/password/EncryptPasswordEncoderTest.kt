package com.yourssu.openssupot.spacehub.domain.support.security.password

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

    @Test
    fun `평문과 암호화된 비밀번호가 같다면 true를 반환한다`() {
        // given
        val encryptPasswordEncoder = EncryptPasswordEncoder()
        val plainPassword = "thisIsPassword1234"

        // when
        val encodedPassword = encryptPasswordEncoder.encode(plainPassword)
        val actual = encryptPasswordEncoder.matches(plainPassword, encodedPassword)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `평문과 암호화된 비밀번호가 다르다면 false를 반환한다`() {
        // given
        val encryptPasswordEncoder = EncryptPasswordEncoder()
        val plainPassword = "thisIsPassword1234"
        val encryptedPassword = encryptPasswordEncoder.encode(plainPassword)
        val differentPassword = "differentPassword1234"

        // when
        val actual = encryptPasswordEncoder.matches(differentPassword, encryptedPassword)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `평문 비밀번호와 암호화한 비밀번호가 동일한 비밀번호인지 확인할 때, 암호화된 비밀번호가 빈 값이면 false를 반환한다`() {
        // given
        val encryptPasswordEncoder = EncryptPasswordEncoder()
        val emptyEncodedPassword = " "

        // when
        val actual = encryptPasswordEncoder.matches("rawPassword", emptyEncodedPassword)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `평문 비밀번호와 암호화한 비밀번호가 동일한 비밀번호인지 확인할 때, 암호화된 비밀번호가 암호화 형식에 맞지 않으면 false를 반환한다`() {
        // given
        val encryptPasswordEncoder = EncryptPasswordEncoder()
        val invalidFormatEncodedPassword = "invalidFormat"

        // when
        val actual = encryptPasswordEncoder.matches("rawPassword", invalidFormatEncodedPassword)

        // then
        assertThat(actual).isFalse()
    }
}
