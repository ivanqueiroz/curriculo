package dev.ivanqueiroz.curriculo.rest.curriculo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.curriculo.Curriculo
import dev.ivanqueiroz.curriculo.rest.conhecimento.ConhecimentoResource
import dev.ivanqueiroz.curriculo.rest.contato.ContatoResource
import dev.ivanqueiroz.curriculo.rest.historico.HistoricoResource
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa o curriculo.")
class CurriculoResource @JsonCreator constructor(@JsonIgnore val curriculo: Curriculo) : RepresentationModel<CurriculoResource>() {

    @Schema(description = "Identificador único do curriculo.", example = "1", required = true)
    val id: Long = curriculo.id

    @Schema(description = "Primeiro Nome.", example = "Ivan")
    @JsonProperty("primeiroNome")
    val primeiroNome: String = curriculo.primeiroNome

    @Schema(description = "Último Nome.", example = "Queiroz")
    @JsonProperty("ultimoNome")
    val ultimoNome: String = curriculo.ultimoNome

    @Schema(description = "Profissão", example = "Arquiteto de Software")
    @JsonProperty("profissao")
    val profissao: String = curriculo.profissao

    @Schema(description = "Resumo", example = "Experiência em mais de 10 anos...")
    @JsonProperty("resumo")
    val resumo: String = curriculo.resumo

    @Schema(description = "Lista com o históriocos de experiências, formações e atividades", example = "Formação, Palestras etc")
    @JsonProperty("historicos")
    val historicos: List<HistoricoResource> = curriculo.historicos.orEmpty().map { h -> HistoricoResource(h) }

    @Schema(description = "Lista de meios de contato", example = "Telefone, email.")
    @JsonProperty("contatos")
    val contatos: List<ContatoResource> = curriculo.contatos.orEmpty().map { c -> ContatoResource(c) }

    @Schema(description = "Lista de conhecimentos", example = "Telefone, email.")
    @JsonProperty("conhecimentos")
    val conhecimentos: List<ConhecimentoResource> = curriculo.conhecimentos.orEmpty().map { c -> ConhecimentoResource(c) }

    init {
        add(linkTo(methodOn(CurriculoRestController::class.java).obterCurriculo()).withSelfRel())
    }

}
