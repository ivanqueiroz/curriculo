package dev.ivanqueiroz.curriculo.infraestrutura.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket



@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun greetingApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.ivanqueiroz.curriculo"))
                .build()
                .apiInfo(metaData())

    }

    private fun metaData(): ApiInfo {
        return ApiInfoBuilder()
                .title("Curriculo Ivan Queiroz REST API")
                .description("\"REST API para interessados em informações\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build()
    }

    protected fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

}