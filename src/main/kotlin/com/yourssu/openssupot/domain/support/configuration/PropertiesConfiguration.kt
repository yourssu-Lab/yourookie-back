package com.yourssu.openssupot.domain.support.configuration

import com.yourssu.openssupot.domain.support.security.token.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class PropertiesConfiguration {
}
