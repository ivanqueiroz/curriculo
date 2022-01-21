package dev.ivanqueiroz.curriculo.rest.historico

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Históricos", description = "Históricos de formação e experiencias")
class HistoricoRestController {

    @Autowired
    lateinit var historicoRestService: HistoricoRestService

    fun todos(): ResponseEntity<List<HistoricoResource>>{
        val historicos= historicoRestService.obtertTodosHistoricos().map { h-> HistoricoResource(h) }
        return ResponseEntity(historicos, HttpStatus.OK)
    }

    @Operation(summary = "Obter todas as experiências")
    @GetMapping("/experiencias")
    fun experiencias(): ResponseEntity<List<ExperienciaResource>> {
        val experiencias = historicoRestService.obterTodasExperiencias()
        return ResponseEntity(experiencias, HttpStatus.OK)
    }

    @Operation(summary = "Obter um conhecimento pelo id")
    @GetMapping(value = ["/experiencias/{id}"])
    fun experiencia(@PathVariable id: Long): HttpEntity<ExperienciaResource> {
        val experienciaResource = historicoRestService.obterExperienciaPorId(id)
        return ResponseEntity(experienciaResource, HttpStatus.OK)
    }

    @Operation(summary = "Obter todas as certificações")
    @GetMapping("/certificacoes")
    fun certificacoes(): ResponseEntity<List<CertificacaoResource>> {
        val certificacoes = historicoRestService.obterTodasCertificacoes()
        return ResponseEntity(certificacoes, HttpStatus.OK)
    }

    @Operation(summary = "Obter certificação pelo id")
    @GetMapping(value = ["/certificacao/{id}"])
    fun certificacao(@PathVariable id: Long): HttpEntity<CertificacaoResource> {
        val certificacaoResource = historicoRestService.obterCertificacaoPorId(id)
        return ResponseEntity(certificacaoResource, HttpStatus.OK)
    }

    @Operation(summary = "Obter todas as formações")
    @GetMapping("/formacoes")
    fun formacoes(): ResponseEntity<List<FormacaoResource>> {
        val formacoes = historicoRestService.obterTodasFormacoes()
        return ResponseEntity(formacoes, HttpStatus.OK)
    }

    @Operation(summary = "Obter formação pelo id")
    @GetMapping(value = ["/formacao/{id}"])
    fun formacao(@PathVariable id: Long): HttpEntity<FormacaoResource> {
        val formacaoResource = historicoRestService.obterFormacaoPorId(id)
        return ResponseEntity(formacaoResource, HttpStatus.OK)
    }

    @Operation(summary = "Obter todas as palestras aplicadas")
    @GetMapping("/palestras")
    fun palestras(): ResponseEntity<List<PalestraResource>> {
        val palestras = historicoRestService.obterTodasPalestras()
        return ResponseEntity(palestras, HttpStatus.OK)
    }

    @Operation(summary = "Obter palestra pelo id")
    @GetMapping(value = ["/palestra/{id}"])
    fun palestra(@PathVariable id: Long): HttpEntity<PalestraResource> {
        val formacaoResource = historicoRestService.obterPalestraPorId(id)
        return ResponseEntity(formacaoResource, HttpStatus.OK)
    }

    @Operation(summary = "Obter todos os treinamentos aplicados")
    @GetMapping("/treinamentos")
    fun treinamentos(): ResponseEntity<List<TreinamentoResource>> {
        val palestras = historicoRestService.obterTodosTreinamentos()
        return ResponseEntity(palestras, HttpStatus.OK)
    }

    @Operation(summary = "Obter treinamento pelo id")
    @GetMapping(value = ["/treinamento/{id}"])
    fun treinamento(@PathVariable id: Long): HttpEntity<TreinamentoResource> {
        val treinamentoResource = historicoRestService.obterTreinamentoPorId(id)
        return ResponseEntity(treinamentoResource, HttpStatus.OK)
    }
}
