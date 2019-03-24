package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder

@ApiModel(description = "Classe que representa um treinamento realizado.")
class TreinamentoResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : ResourceSupport(){

    @ApiModelProperty(notes = "Identificador único do treinamento.", example = "1", required = true, position = 0)
    val id: Long = historico.id

    @ApiModelProperty(notes = "Local onde foi realizado o treinamento.", example = "UCSAL", position = 1)
    @JsonProperty("local")
    val empresa: String = historico.instituicao

    @ApiModelProperty(notes = "Cargo ocupado durante a experiência.", example = "Analista de Sistemas", position = 2)
    @JsonProperty("cargo")
    val titulo: String = historico.titulo

    @ApiModelProperty(notes = "Resumo das atividades exercidas.", example = "Monitoria e suporte da infra de telefonia...", position = 3)
    @JsonProperty("resumo")
    val resumo: String = historico.descricao

    @ApiModelProperty(notes = "Ano do treinamento.", example = "2017", position = 5)
    @JsonProperty("ano")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(HistoricoRestController::class.java).treinamento(id)).withSelfRel())
    }

}