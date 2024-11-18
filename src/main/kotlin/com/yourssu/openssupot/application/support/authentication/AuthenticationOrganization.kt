package com.yourssu.openssupot.application.support.authentication

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationOrganization(
    val required: Boolean = true
)
