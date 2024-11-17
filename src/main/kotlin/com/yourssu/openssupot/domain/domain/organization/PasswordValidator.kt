package com.yourssu.openssupot.domain.domain.organization

import java.util.regex.Matcher
import java.util.regex.Pattern

class PasswordValidator {

    companion object {
        private const val PASSWORD_REGEX = "^(?=(.*[a-zA-Z]))(?=(.*\\d))[a-zA-Z\\d!@#\$%^&*()_+=-]{8,}\$"

        fun validate(rawPassword: String) {
            validateNotBlank(rawPassword)
            validatePasswordFormat(rawPassword)
        }

        private fun validateNotBlank(rawPassword: String) {
            if (rawPassword.isBlank()) {
                throw InvalidPasswordException("비밀번호가 빈 값입니다.")
            }
        }

        private fun validatePasswordFormat(rawPassword: String) {
            val pattern: Pattern = Pattern.compile(PASSWORD_REGEX)
            val matcher: Matcher = pattern.matcher(rawPassword)
            if (!matcher.matches()) {
                throw InvalidPasswordException("비밀번호는 영어+숫자 8글자 이상이어야 합니다.")
            }
        }
    }
}
