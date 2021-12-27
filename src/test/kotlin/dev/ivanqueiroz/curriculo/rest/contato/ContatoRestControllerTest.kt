package dev.ivanqueiroz.curriculo.rest.contato

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
class ContatoRestControllerTest : CurriculoApplicationTests() {

    @LocalServerPort
    private val port: Int = 0

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var contatoRestController: ContatoRestController

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @BeforeEach
    fun setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(contatoRestController).build()
    }

    @Test
    fun contato() {
        print(
            this.restTemplate.getForObject(
                "http://localhost:$port/contatos/1",
                String::class.java
            )
        )
        Assertions.assertThat(
            this.restTemplate.getForObject(
                "http://localhost:$port/contatos/1",
                String::class.java
            )
        ).contains("links")
    }

    @Test
    fun contatoPorTipo() {
        print(
            this.restTemplate.getForObject(
                "http://localhost:$port/contatos?tipo=telefone",
                String::class.java
            )
        )
        Assertions.assertThat(
            this.restTemplate.getForObject(
                "http://localhost:$port/contatos?tipo=telefone",
                String::class.java
            )
        ).contains("links")
    }

    @Test
    fun contatos() {
        print(
            this.restTemplate.getForObject(
                "http://localhost:$port/contatos/lista",
                String::class.java
            )
        )
        Assertions.assertThat(
            this.restTemplate.getForObject(
                "http://localhost:$port/contatos/lista",
                String::class.java
            )
        ).contains("links")
    }


}
