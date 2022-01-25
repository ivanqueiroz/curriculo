package dev.ivanqueiroz.curriculo.rest.historico

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/historicos")
@Tag(name = "Históricos", description = "Históricos de formação e experiencias")
class HistoricoRestController {

    @Autowired
    lateinit var historicoRestService: HistoricoRestService

    @PostMapping("/experiencias")
    @PreAuthorize("hasAuthority('LEITURA_ESCRITA')")
    fun experiencia(@RequestBody experienciaResource: ExperienciaResource): ResponseEntity<ExperienciaResource> {
        val historico = historicoRestService.inserirExperiencia(experienciaResource)
        return ResponseEntity(
            ExperienciaResource(
                titulo = historico.titulo,
                anoInicio = historico.anoInicio,
                empresa = historico.instituicao,
                resumo = historico.descricao,
                anoFim = historico.anoFim
            ), HttpStatus.OK
        )
    }

    @PutMapping("/experiencias")
    @PreAuthorize("hasAuthority('LEITURA_ESCRITA')")
    fun atualiza(@RequestBody experienciaResource: ExperienciaResource): ResponseEntity<ExperienciaResource> {
        val historico = historicoRestService.atualizarExperiencia(experienciaResource)
        return ResponseEntity(
            ExperienciaResource(
                titulo = historico.titulo,
                anoInicio = historico.anoInicio,
                empresa = historico.instituicao,
                resumo = historico.descricao,
                anoFim = historico.anoFim
            ), HttpStatus.OK
        )
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
