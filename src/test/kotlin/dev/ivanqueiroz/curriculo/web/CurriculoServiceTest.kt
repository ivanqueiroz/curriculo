package dev.ivanqueiroz.curriculo.web

import dev.ivanqueiroz.curriculo.CurriculoApplicationTests
import dev.ivanqueiroz.curriculo.web.curriculo.CurriculoService
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = ["classpath:application.yml"])
class CurriculoServiceTest : CurriculoApplicationTests(){

    @Autowired
    lateinit var curriculoService: CurriculoService

    @Test
    fun obterHistoricoFormacao() {
        val obterHistoricoFormacao = curriculoService.obterHistoricoFormacao()

        print(obterHistoricoFormacao)

        assert(obterHistoricoFormacao[0].anoFim == "2007")
    }
}
