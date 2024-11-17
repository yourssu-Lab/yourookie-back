package com.yourssu.openssupot.domain.domain.organization

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import kotlin.test.Test

@Suppress("NonAsciiCharacters")
class OrganizationNameTest {

    @Test
    fun `빈 단체 이름을 생성하면 예외가 발생한다`() {
        // given
        val emptyName = "  "

        // when & then
        assertThatThrownBy { OrganizationName(emptyName) }
            .isInstanceOf(InvalidOrganizationNameException::class.java)
            .hasMessage("단체 이름이 빈 값입니다.")
    }

    @Test
    fun `단체 이름의 길이가 유효하지 않으면 예외가 발생한다`() {
        // given
        val longName = "a".repeat(21)

        // when & then
        assertThatThrownBy { OrganizationName(longName) }
            .isInstanceOf(InvalidOrganizationNameException::class.java)
            .hasMessage("단체 이름은 1~20 글자여야 합니다.")
    }

    @Test
    fun `유효한 단체 이름이라면 객체가 정상적으로 생성된다`() {
        // given
        val validName = "Valid Name"

        // when
        val organizationName = OrganizationName(validName)

        // then
        assertThat(organizationName.name).isEqualTo(validName)
    }
}
