package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder

@ApiModel(description = "Classe que representa o curriculo.")
class HistoricoResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : ResourceSupport() {

    @ApiModelProperty(notes = "Identificador único de histórico.", example = "1", required = true, position = 0)
    val id: Long = historico.id

    @ApiModelProperty(notes = "Instituição que o histórico está ligado.", example = "Indra", position = 1)
    @JsonProperty("instituicao")
    val instituicao: String = historico.instituicao

    @ApiModelProperty(notes = "Titulo do histórico.", example = "Analista de Sistemas", position = 2)
    @JsonProperty("titulo")
    val titulo: String = historico.titulo

    @ApiModelProperty(notes = "Descrição do histórico.", example = "Monitoria e suporte da infra de telefonia...", position = 3)
    @JsonProperty("descricao")
    val resumo: String = historico.descricao

    @ApiModelProperty(notes = "Ano do início do histórico.", example = "2016", position = 4)
    @JsonProperty("inicio")
    val anoInicio: String = historico.anoInicio

    @ApiModelProperty(notes = "Ano do fim do histórico.", example = "2017", position = 5)
    @JsonProperty("fim")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(HistoricoRestController::class.java).experiencia(id)).withSelfRel())
    }
}