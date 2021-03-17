package io.github.edmaputra.uwati.profile.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun customOpenApi(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("Uwati Profile API")
                .description("API for Profile Management on Uwati Application")
                .version("0.0.2")
                .contact(
                    Contact()
                        .name("Bangun Edmasaputra")
                        .email("bangun.edma.saputra@gmail.com")
                )
        )
        .servers(
            arrayListOf(
                Server()
                    .url("http://localhost:10010")
                    .description("Development Server")
            )
        )
}