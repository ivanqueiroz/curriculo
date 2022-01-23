package dev.ivanqueiroz.curriculo.rest.historico

import dev.ivanqueiroz.curriculo.CurriculoApplicationTests
import org.assertj.core.api.Assertions.assertThat
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
@TestPropertySource(locations = ["classpath:application.yml"])
class HistoricoRestControllerTest : CurriculoApplicationTests() {

    @LocalServerPort
    private val port: Int = 0

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var historicoRestController: HistoricoRestController

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @BeforeEach
    fun setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(historicoRestController).build()
    }

    @Test
    fun experiencias() {
        print(this.restTemplate.getForObject("http://localhost:$port/historicos/experiencias",
                String::class.java))
        assertThat(this.restTemplate.getForObject("http://localhost:$port/historicos/experiencias",
                String::class.java)).contains("links")
    }

    @Test
    fun experiencia(){
        print(this.restTemplate.getForObject("http://localhost:$port/historicos/experiencias/1",
                String::class.java))
        assertThat(this.restTemplate.getForObject("http://localhost:$port/historicos/experiencias/1",
                String::class.java)).contains("links")
    }

}
