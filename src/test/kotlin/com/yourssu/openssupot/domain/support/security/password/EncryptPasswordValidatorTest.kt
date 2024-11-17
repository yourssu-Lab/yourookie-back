package com.yourssu.openssupot.domain.support.security.password

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class EncryptPasswordValidatorTest {

    @Test
    fun `암호화되지 않은 비밀번호 여부를 확인할 때 비밀번호가 비어있다면 true를 반환한다`() {
        // given
        val emptyEncryptPassword = " "

        // when
        val actual = EncryptPasswordValidator.isNotEncrypted(emptyEncryptPassword)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `암호화되지 않은 비밀번호 여부를 확인할 때, 비밀번호가 암호화 형식에 맞지 않으면 true를 반환한다`() {
        // given
        val invalidFormatEncryptPassword = "invalidFormat"

        // when
        val actual = EncryptPasswordValidator.isNotEncrypted(invalidFormatEncryptPassword)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `암호화되지 않은 비밀번호 여부를 확인할 때, 비밀번호가 암호화 형식에 맞으면 false를 반환한다`() {
        // given
        val encryptPassword = "\$2a\$10\$SG1qTzy5vDOLYaPQ5ws/aA+K1mG2ekX+IuE8EXk/xhF0RQoNlXsXl"

        // when
        val actual = EncryptPasswordValidator.isNotEncrypted(encryptPassword)

        // then
        assertThat(actual).isFalse()
    }
}
