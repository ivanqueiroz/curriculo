package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@ApiModel(description = "Classe que representa uma certificação realizada.")
class CertificacaoResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<CertificacaoResource>() {

    @ApiModelProperty(notes = "Identificador único de experiência.", example = "1", required = true, position = 0)
    val id: Long = historico.id

    @ApiModelProperty(notes = "Titulo da certificação.", example = "OCJP", position = 1)
    @JsonProperty("titulo")
    val titulo: String = historico.titulo

    @ApiModelProperty(notes = "Instituição responsável pela certificação.", example = "Oracle", position = 2)
    @JsonProperty("instituicao")
    val resumo: String = historico.instituicao

    @ApiModelProperty(notes = "Ano da certificação", example = "2017", position = 3)
    @JsonProperty("ano")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).certificacao(id)).withSelfRel())
    }
}
