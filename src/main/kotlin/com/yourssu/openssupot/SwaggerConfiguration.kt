import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {
    @Bean
    fun openAPI(): OpenAPI {
        val apiInfo = Info().title("OpenSSUpot API Document").version("v0.0.1")
        return OpenAPI()
            .info(apiInfo)
            .components(components())
            .addServersItem(Server().url("/"))
    }

    private fun components(): Components {
        return Components()
    }
}
