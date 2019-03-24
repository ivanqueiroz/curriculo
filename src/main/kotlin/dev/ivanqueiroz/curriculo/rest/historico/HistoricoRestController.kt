package dev.ivanqueiroz.curriculo.rest.historico

import dev.ivanqueiroz.curriculo.dominio.historico.TipoHistorico
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/historicos")
class HistoricoRestController {

    @Autowired
    lateinit var historicoService: HistoricoService

    @GetMapping("/experiencias")
    fun experiencias(): ResponseEntity<List<ExperienciaResource>> {
        val experiencias = historicoService.obterTodasExperiencias()
        return ResponseEntity(experiencias, HttpStatus.OK)
    }

    @GetMapping(value = ["/experiencias/{id}"])
    fun experiencia(@PathVariable id: Long): HttpEntity<ExperienciaResource> {
        val experienciaResource = historicoService.obterExperienciaPorId(id)
        return ResponseEntity<ExperienciaResource>(experienciaResource, HttpStatus.OK)
    }
}