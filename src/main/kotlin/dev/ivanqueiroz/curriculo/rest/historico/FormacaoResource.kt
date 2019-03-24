package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder

@ApiModel(description = "Classe que representa uma formação realizada ou em andamento.")
class FormacaoResource@JsonCreator constructor(@JsonIgnore val historico: Historico) : ResourceSupport() {

    @ApiModelProperty(notes = "Identificador único da formação.", example = "1", required = true, position = 0)
    val id: Long = historico.id

    @ApiModelProperty(notes = "Título da formação.", example = "Bacharelado em Sistemas de Informação", position = 1)
    @JsonProperty("formacao")
    val titulo: String = historico.titulo

    @ApiModelProperty(notes = "Detalhamento da formação.", example = "Completado em junho de 2012 no Centro Universitário Estácio da Bahia", position = 2)
    @JsonProperty("resumo")
    val resumo: String = historico.descricao

    @ApiModelProperty(notes = "Ano de conclusão.", example = "2017", position = 3)
    @JsonProperty("conclusao")
    var anoFim: String =  historico.anoFim
        get() = if("" ==  historico.anoFim) "Atual" else  historico.anoFim

    init{
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(HistoricoRestController::class.java).formacao(id)).withSelfRel())
    }
}