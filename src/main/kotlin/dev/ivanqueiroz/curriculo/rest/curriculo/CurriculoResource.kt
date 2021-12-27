package dev.ivanqueiroz.curriculo.rest.curriculo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.curriculo.Curriculo
import dev.ivanqueiroz.curriculo.rest.conhecimento.ConhecimentoResource
import dev.ivanqueiroz.curriculo.rest.contato.ContatoResource
import dev.ivanqueiroz.curriculo.rest.historico.HistoricoResource
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@ApiModel(description = "Classe que representa o curriculo.")
class CurriculoResource @JsonCreator constructor(@JsonIgnore val curriculo: Curriculo) : RepresentationModel<CurriculoResource>() {

    @ApiModelProperty(notes = "Identificador único do curriculo.", example = "1", required = true, position = 0)
    val id: Long = curriculo.id

    @ApiModelProperty(notes = "Primeiro Nome.", example = "Ivan", position = 1)
    @JsonProperty("primeiroNome")
    val primeiroNome: String = curriculo.primeiroNome

    @ApiModelProperty(notes = "Último Nome.", example = "Queiroz", position = 1)
    @JsonProperty("ultimoNome")
    val ultimoNome: String = curriculo.ultimoNome

    @ApiModelProperty(notes = "Profissão", example = "Arquiteto de Software", position = 1)
    @JsonProperty("profissao")
    val profissao: String = curriculo.profissao

    @ApiModelProperty(notes = "Resumo", example = "Experiência em mais de 10 anos...", position = 1)
    @JsonProperty("resumo")
    val resumo: String = curriculo.resumo

    @ApiModelProperty(notes = "Lista com o históriocos de experiências, formações e atividades", example = "Formação, Palestras etc", position = 1)
    @JsonProperty("historicos")
    val historicos: List<HistoricoResource> = curriculo.historicos.orEmpty().map { h -> HistoricoResource(h) }

    @ApiModelProperty(notes = "Lista de meios de contato", example = "Telefone, email.", position = 1)
    @JsonProperty("contatos")
    val contatos: List<ContatoResource> = curriculo.contatos.orEmpty().map { c -> ContatoResource(c) }

    @ApiModelProperty(notes = "Lista de conhecimentos", example = "Telefone, email.", position = 1)
    @JsonProperty("conhecimentos")
    val conhecimentos: List<ConhecimentoResource> = curriculo.conhecimentos.orEmpty().map { c -> ConhecimentoResource(c) }

    init {
        add(linkTo(methodOn(CurriculoRestController::class.java).obterCurriculo()).withSelfRel())
    }

}
