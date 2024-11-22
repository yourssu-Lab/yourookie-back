package com.yourssu.openssupot.spacehub.domain.domain.organization

import com.yourssu.openssupot.spacehub.domain.domain.password.InvalidPasswordException
import com.yourssu.openssupot.spacehub.domain.domain.password.PasswordFormat
import com.yourssu.openssupot.spacehub.domain.domain.password.PasswordValidator
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
        assertThatThrownBy { PasswordValidator.validate(PasswordFormat.ORGANIZATION_PASSWORD, blankPassword) }
            .isInstanceOf(InvalidPasswordException::class.java)
            .hasMessage("비밀번호가 빈 값입니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["short1", "noNumberHere", "123456789"])
    fun `유효하지 않은 단체 비밀번호 형식이 입력되면 예외가 발생한다`(invalidPassword: String) {
        // when & then
        assertThatThrownBy { PasswordValidator.validate(PasswordFormat.ORGANIZATION_PASSWORD, invalidPassword) }
            .isInstanceOf(InvalidPasswordException::class.java)
            .hasMessage(PasswordFormat.ORGANIZATION_PASSWORD.errorMessage)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Password1", "secure123", "MyPassw0rd!@#", "Admin1234"])
    fun `유효한 단체 비밀번호 형식이라면 예외가 발생하지 않는다`(validPassword: String) {
        // when & then
        assertDoesNotThrow { PasswordValidator.validate(PasswordFormat.ORGANIZATION_PASSWORD, validPassword) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["123", "aaa", "1a1"])
    fun `유효하지 않은 예약자 비밀번호 형식이 입력되면 예외가 발생한다`(invalidPassword: String) {
        // when & then
        assertThatThrownBy { PasswordValidator.validate(PasswordFormat.PERSONAL_RESERVATION_PASSWORD, invalidPassword) }
            .isInstanceOf(InvalidPasswordException::class.java)
            .hasMessage(PasswordFormat.PERSONAL_RESERVATION_PASSWORD.errorMessage)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1234", "aaaa", "11aa"])
    fun `유효한 예약자 비밀번호 형식이라면 예외가 발생하지 않는다`(validPassword: String) {
        // when & then
        assertDoesNotThrow { PasswordValidator.validate(PasswordFormat.PERSONAL_RESERVATION_PASSWORD, validPassword) }
    }
}
