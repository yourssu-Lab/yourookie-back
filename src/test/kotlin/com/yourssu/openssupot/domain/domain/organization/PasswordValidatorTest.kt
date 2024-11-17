package com.yourssu.openssupot.domain.domain.organization

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
class PasswordValidatorTest {

    @Test
    fun `빈 비밀번호가 입력되면 예외가 발생한다`() {
        // given
        val blankPassword = "  "

        // when & then
        assertThatThrownBy { PasswordValidator.validate(blankPassword) }
            .isInstanceOf(InvalidPasswordException::class.java)
            .hasMessage("비밀번호가 빈 값입니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["short1", "noNumberHere", "123456789"])
    fun `유효하지 않은 비밀번호 형식이 입력되면 예외가 발생한다`(invalidPassword: String) {
        // when & then
        assertThatThrownBy { PasswordValidator.validate(invalidPassword) }
            .isInstanceOf(InvalidPasswordException::class.java)
            .hasMessage("비밀번호는 영어+숫자 8글자 이상이어야 합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["Password1", "secure123", "MyPassw0rd!@#", "Admin1234"])
    fun `유효한 비밀번호 형식이라면 예외가 발생하지 않는다`(validPassword: String) {
        // when & then
        assertDoesNotThrow { PasswordValidator.validate(validPassword) }
    }
}
