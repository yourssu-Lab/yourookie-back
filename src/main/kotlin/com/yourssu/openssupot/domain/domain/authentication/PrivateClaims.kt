package com.yourssu.openssupot.domain.domain.authentication

data class PrivateClaims(
    val userId: Long,
) {

    companion object {
        private const val USER_ID_KEY_NAME = "userId"
    }

    fun toMap(): Map<String, Any> {
        return mapOf(USER_ID_KEY_NAME to userId)
    }
}
