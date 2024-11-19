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
    val hashtags: MutableList<Hashtag> = mutableListOf(),
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

    fun getHashtagValues(): List<String> = hashtags.map { it.name }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Organization

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
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

    fun addHashtags(tags: List<Hashtag>) {
        hashtags.addAll(tags)
    }
}
