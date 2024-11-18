package com.yourssu.openssupot.application.support.configuration

import com.yourssu.openssupot.application.support.authentication.AuthenticationInterceptor
import com.yourssu.openssupot.application.support.authentication.AuthenticationOrganizationInfoArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration(
    private val authenticationInterceptor: AuthenticationInterceptor,
    val authenticationOrganizationInfoArgumentResolver: AuthenticationOrganizationInfoArgumentResolver
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns("/**")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(authenticationOrganizationInfoArgumentResolver)
    }
}
