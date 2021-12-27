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


@ApiModel(description = "Classe que representa uma palestra aplicada.")
class PalestraResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<PalestraResource>() {

    @ApiModelProperty(notes = "Identificador único da palestra.", example = "1", required = true, position = 0)
    val id: Long = historico.id

    @ApiModelProperty(notes = "Local onde foi realizada a palestra", example = "UCSAL", position = 1)
    @JsonProperty("local")
    val empresa: String = historico.instituicao

    @ApiModelProperty(notes = "Titulo da palestra.", example = "Neo4J no mundo real", position = 2)
    @JsonProperty("titulo")
    val titulo: String = historico.titulo

    @ApiModelProperty(notes = "Nome do evento o qual a palestra foi aplicada.", example = "Simpósio de TI", position = 3)
    @JsonProperty("evento")
    val resumo: String = historico.descricao

    @ApiModelProperty(notes = "Ano da ocorrência da palestra.", example = "2017", position = 4)
    @JsonProperty("ano")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    @ApiModelProperty(notes = "Link com informações do treinamento.", example = "http://www.nosqlba.org/2017/index.html", position = 5)
    @JsonProperty("linkReferencia")
    val linkReferencia: String = historico.linkReferencia

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).palestra(id)).withSelfRel())
    }
}
