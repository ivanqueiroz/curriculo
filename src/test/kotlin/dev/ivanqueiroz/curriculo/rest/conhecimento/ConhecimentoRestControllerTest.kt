package dev.ivanqueiroz.curriculo.rest.conhecimento

import dev.ivanqueiroz.curriculo.CurriculoApplicationTests
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:teste.properties"])
class ConhecimentoRestControllerTest : CurriculoApplicationTests() {

    @LocalServerPort
    private val port: Int = 0

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var conhecimentoRestController: ConhecimentoRestController

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @BeforeEach
    fun setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(conhecimentoRestController).build()
    }

    @Test
    fun conhecimento() {

        print(
            this.restTemplate.getForObject(
                "http://localhost:$port/conhecimentos/1",
                String::class.java
            )
        )
        Assertions.assertThat(
            this.restTemplate.getForObject(
                "http://localhost:$port/conhecimentos/1",
                String::class.java
            )
        ).contains("links")

    }

    @Test
    fun conhecimentos() {

        print(
            this.restTemplate.getForObject(
                "http://localhost:$port/conhecimentos/lista",
                String::class.java
            )
        )
        Assertions.assertThat(
            this.restTemplate.getForObject(
                "http://localhost:$port/conhecimentos/lista",
                String::class.java
            )
        ).contains("links")
    }

    @Test
    fun conhecimentosPorAssunto() {

        print(
            this.restTemplate.getForObject(
                "http://localhost:$port/conhecimentos?assunto=Java",
                String::class.java
            )
        )
        Assertions.assertThat(
            this.restTemplate.getForObject(
                "http://localhost:$port/conhecimentos?assunto=Java",
                String::class.java
            )
        ).contains("links")
    }

    @Test
    fun documentacao() {
        print(
            this.restTemplate.getForObject(
                "http://localhost:$port/swagger-ui.html",
                String::class.java
            )
        )
        Assertions.assertThat(
            this.restTemplate.getForObject(
                "http://localhost:$port/swagger-ui.html",
                String::class.java
            )
        ).contains("swagger")
    }
}
