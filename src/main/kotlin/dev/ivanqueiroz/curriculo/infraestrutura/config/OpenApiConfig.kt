package dev.ivanqueiroz.curriculo.infraestrutura.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry


@Configuration
class OpenApiConfig {

    @Bean
    fun curriculoApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Curriculo Ivan Queiroz REST API")
                    .description("\"REST API de meu curriculo profissional\"")
                    .version("1.0.0")
                    .license(License().name("Apache License Version 2.0").url("https://www.apache.org/licenses/LICENSE-2.0\""))
            )

    }

    protected fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

}
