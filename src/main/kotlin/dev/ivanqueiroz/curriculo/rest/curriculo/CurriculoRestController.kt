package dev.ivanqueiroz.curriculo.rest.curriculo

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/curriculo")
@Tag(name = "Curriculo", description = "Representação do curriculo completo")
class CurriculoRestController {

    @Autowired
    lateinit var curriculoRestService: CurriculoRestService

    @GetMapping
    @Operation(summary = "Obter curriculo.")
    fun obterCurriculo(): HttpEntity<CurriculoResource> {
        return ResponseEntity(curriculoRestService.obterCurriculoPortugues(), HttpStatus.OK)
    }
}
