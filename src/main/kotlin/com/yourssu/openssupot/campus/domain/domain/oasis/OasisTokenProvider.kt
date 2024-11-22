package com.yourssu.openssupot.campus.domain.domain.oasis

import java.util.concurrent.locks.ReentrantLock
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import kotlin.concurrent.withLock

@Component
class OasisTokenProvider(

    private val oasisClient: OasisClient,

    @Value("\${oasis.loginId}")
    private val loginId: String,

    @Value("\${oasis.password}")
    private val password: String,
) {

    private var accessToken: String? = null
    private var lock = ReentrantLock()

    fun getAccessToken(): String {
        return accessToken ?: fetchNewAccessToken()
    }

    fun fetchNewAccessToken(): String {
        lock.withLock {
            if (accessToken == null) {
                val loginRequest = LoginRequest(loginId = loginId, password = password)
                val loginResponse: LoginResponse = oasisClient.login(loginRequest)

                accessToken = loginResponse.data?.accessToken
                    ?: throw IllegalStateException("Failed to get access token from Oasis")
            }
        }
        return accessToken ?: throw IllegalStateException("Failed to get access token from Oasis")
    }

    fun invalidateAccessToken() {
        lock.withLock {
            accessToken = null
        }
    }
}
