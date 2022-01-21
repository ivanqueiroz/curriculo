package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn


@Schema(description = "Classe que representa uma palestra aplicada.")
class PalestraResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<PalestraResource>() {

    @Schema(description = "Identificador único da palestra.", example = "1", required = true)
    val id: Long = historico.id

    @Schema(description = "Local onde foi realizada a palestra", example = "UCSAL")
    @JsonProperty("local")
    val empresa: String = historico.instituicao

    @Schema(description = "Titulo da palestra.", example = "Neo4J no mundo real")
    @JsonProperty("titulo")
    val titulo: String = historico.titulo

    @Schema(description = "Nome do evento o qual a palestra foi aplicada.", example = "Simpósio de TI")
    @JsonProperty("evento")
    val resumo: String = historico.descricao

    @Schema(description = "Ano da ocorrência da palestra.", example = "2017")
    @JsonProperty("ano")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    @Schema(description = "Link com informações do treinamento.", example = "http://www.nosqlba.org/2017/index.html")
    @JsonProperty("linkReferencia")
    val linkReferencia: String = historico.linkReferencia

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).palestra(id)).withSelfRel())
    }
}
