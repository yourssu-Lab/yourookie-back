package com.yourssu.openssupot.campus.domain.support.configuration

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.yourssu.openssupot.campus"])
class OpenFeignConfiguration {
}
