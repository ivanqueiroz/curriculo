package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa o curriculo.")
class HistoricoResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<HistoricoResource>() {

    @Schema(description = "Identificador único de histórico.", example = "1", required = true)
    val id: Long = historico.id

    @Schema(description = "Instituição que o histórico está ligado.", example = "Indra")
    @JsonProperty("instituicao")
    val instituicao: String = historico.instituicao

    @Schema(description = "Titulo do histórico.", example = "Analista de Sistemas")
    @JsonProperty("titulo")
    val titulo: String = historico.titulo

    @Schema(description = "Descrição do histórico.", example = "Monitoria e suporte da infra de telefonia...")
    @JsonProperty("descricao")
    val resumo: String = historico.descricao

    @Schema(description = "Ano do início do histórico.", example = "2016")
    @JsonProperty("inicio")
    val anoInicio: String = historico.anoInicio

    @Schema(description = "Ano do fim do histórico.", example = "2017")
    @JsonProperty("fim")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).experiencia(id)).withSelfRel())
    }
}
