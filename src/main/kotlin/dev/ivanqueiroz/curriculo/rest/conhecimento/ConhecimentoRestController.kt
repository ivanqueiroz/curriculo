package dev.ivanqueiroz.curriculo.rest.conhecimento

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/conhecimentos")
@Tag(name = "Conhecimentos", description = "Conhecimentos profissionais")
class ConhecimentoRestController {

    @Autowired
    lateinit var conhecimentoRestService: ConhecimentoRestService

    @Operation(summary = "Obter um conhecimento pelo id")
    @GetMapping(value = ["/{id}"])
    fun conhecimento(@PathVariable id: Long): HttpEntity<ConhecimentoResource> {
        val contatoResource = conhecimentoRestService.obterConhecimentoPorId(id)
        return ResponseEntity(contatoResource, HttpStatus.OK)
    }

    @Operation(summary = "Obter todos os conhecimentos")
    @GetMapping("/lista")
    fun conhecimentos(): ResponseEntity<List<ConhecimentoResource>> {
        val todosConhecimentos = conhecimentoRestService.obterTodosConhecimentos()
        return ResponseEntity(todosConhecimentos, HttpStatus.OK)
    }

    @Operation(summary = "Obter todos os conhecimentos referentes a um assunto informado")
    @GetMapping
    fun conhecimentosPorAssunto(@RequestParam(required = false, name = "assunto") assunto: String?): ResponseEntity<List<ConhecimentoResource>> {
        assunto?.let {
            val conhecimentosPeloAssunto = conhecimentoRestService.obterConhecimentoPeloAssunto(it)
            return ResponseEntity(conhecimentosPeloAssunto, HttpStatus.OK)
        }
        return conhecimentos()
    }
}
