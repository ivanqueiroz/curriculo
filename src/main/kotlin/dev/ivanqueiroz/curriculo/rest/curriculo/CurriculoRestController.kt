package dev.ivanqueiroz.curriculo.rest.curriculo

import dev.ivanqueiroz.curriculo.rest.historico.ExperienciaResource
import dev.ivanqueiroz.curriculo.web.curriculo.CurriculoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/curriculo")
@Api(value = "Curriculo", description = "Representação do curriculo completo")
class CurriculoRestController {

    @Autowired
    lateinit var curriculoRestService: CurriculoRestService

    @GetMapping
    @ApiOperation(value = "Obter curriculo.")
    fun obterCurriculo(): HttpEntity<CurriculoResource> {
        return ResponseEntity(curriculoRestService.obterCurriculoPortugues(), HttpStatus.OK)
    }
}