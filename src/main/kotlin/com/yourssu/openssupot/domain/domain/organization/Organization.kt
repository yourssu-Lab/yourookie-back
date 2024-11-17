package com.yourssu.openssupot.domain.domain.organization

import com.yourssu.openssupot.domain.support.security.password.EncryptPasswordValidator

class Organization(
    val id: Long? = null,
    val email: Email,
    val encryptedPassword: String,
    val name: OrganizationName,
    val logoImageUrl: String? = null,
    val description: String? = null,
    val encryptedReservationPassword: String,
) {

    init {
        if (EncryptPasswordValidator.isNotEncrypted(encryptedPassword)) {
            throw PasswordNotEncryptedException("비밀번호가 암호화되지 않았습니다.")
        }

        if (EncryptPasswordValidator.isNotEncrypted(encryptedReservationPassword)) {
            throw PasswordNotEncryptedException("예약 비밀번호가 암호화되지 않았습니다.")
        }
    }

    fun getEmailValue(): String {
        return email.emailAddress
    }

    fun getNameValue(): String {
        return name.name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Organization

        if (id != other.id) return false
        if (email != other.email) return false
        if (encryptedPassword != other.encryptedPassword) return false
        if (name != other.name) return false
        if (logoImageUrl != other.logoImageUrl) return false
        if (description != other.description) return false
        if (encryptedReservationPassword != other.encryptedReservationPassword) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + email.hashCode()
        result = 31 * result + encryptedPassword.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (logoImageUrl?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + encryptedReservationPassword.hashCode()
        return result
    }

    override fun toString(): String {
        return "Organization(" +
                "id=$id, " +
                "email=$email, " +
                "encryptedPassword='$encryptedPassword', " +
                "name=$name, " +
                "logoImageUrl=$logoImageUrl, " +
                "description=$description, " +
                "encryptedReservationPassword='$encryptedReservationPassword')"
    }
}
