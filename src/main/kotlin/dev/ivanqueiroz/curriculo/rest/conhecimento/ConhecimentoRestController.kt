package dev.ivanqueiroz.curriculo.rest.conhecimento

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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

    @PostMapping
    @PreAuthorize("hasAuthority('LEITURA_ESCRITA')")
    fun inserirConhecimento(@RequestBody conhecimentoResource: ConhecimentoResource): HttpEntity<ConhecimentoResource> {
        val contatoResource = conhecimentoRestService.inserirConhecimento(conhecimentoResource)
        return ResponseEntity(contatoResource, HttpStatus.OK)
    }

    @PutMapping
    @PreAuthorize("hasAuthority('LEITURA_ESCRITA')")
    fun atualizarrConhecimento(@RequestBody conhecimentoResource: ConhecimentoResource): HttpEntity<ConhecimentoResource> {
        val contatoResource = conhecimentoRestService.atualizarConhecimento(conhecimentoResource)
        return ResponseEntity(contatoResource, HttpStatus.OK)
    }

    @DeleteMapping(value = ["/{id}"])
    @PreAuthorize("hasAuthority('LEITURA_ESCRITA')")
    fun excluirConhecimento(@PathVariable id: Long): HttpEntity<ConhecimentoResource> {
        conhecimentoRestService.excluirConhecimento(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
