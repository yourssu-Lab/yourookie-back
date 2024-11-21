package com.yourssu.openssupot.domain.domain.organization

import com.yourssu.openssupot.domain.domain.password.PasswordNotEncryptedException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
class OrganizationTest {

    @Test
    fun `암호화되지 않은 비밀번호로 단체를 생성하면 예외가 발생한다`() {
        // given
        val plainPassword = "plainPassword"

        // when & then
        assertThatThrownBy {
            Organization(
                email = Email("email@email.com"),
                encryptedPassword = plainPassword,
                name = OrganizationName("organizationName"),
                logoImageUrl = "logoImageUrl",
                encryptedReservationPassword = "\$2a\$10\$SG1qTzy5vDOLYaPQ5ws/aA+K1mG2ekX+IuE8EXk/xhF0RQoNlXsXl"
            )
        }.isInstanceOf(PasswordNotEncryptedException::class.java)
            .hasMessage("비밀번호가 암호화되지 않았습니다.")
    }

    @Test
    fun `암호화되지 않은 예약 비밀번호로 단체를 생성하면 예외가 발생한다`() {
        // given
        val plainPassword = "plainPassword"

        // when & then
        assertThatThrownBy {
            Organization(
                email = Email("email@email.com"),
                encryptedPassword = "\$2a\$10\$SG1qTzy5vDOLYaPQ5ws/aA+K1mG2ekX+IuE8EXk/xhF0RQoNlXsXl",
                name = OrganizationName("organizationName"),
                logoImageUrl = "logoImageUrl",
                encryptedReservationPassword = plainPassword
            )
        }.isInstanceOf(PasswordNotEncryptedException::class.java)
            .hasMessage("예약 비밀번호가 암호화되지 않았습니다.")
    }

    @Test
    fun `암호화된 비밀번호와 예약 비밀번호로 단체를 생성하면 객체가 정상적으로 생성된다`() {
        // given
        val encryptPassword = "\$2a\$10\$SG1qTzy5vDOLYaPQ5ws/aA+K1mG2ekX+IuE8EXk/xhF0RQoNlXsXl"

        // when & then
        assertDoesNotThrow {
            Organization(
                email = Email("email@email.com"),
                encryptedPassword = encryptPassword,
                name = OrganizationName("organizationName"),
                logoImageUrl = "logoImageUrl",
                encryptedReservationPassword = encryptPassword
            )
        }
    }
}
