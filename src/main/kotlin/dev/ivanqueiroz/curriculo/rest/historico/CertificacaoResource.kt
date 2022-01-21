package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa uma certificação realizada.")
class CertificacaoResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<CertificacaoResource>() {

    @Schema(description = "Identificador único de experiência.", example = "1", required = true)
    val id: Long = historico.id

    @Schema(description = "Titulo da certificação.", example = "OCJP")
    @JsonProperty("titulo")
    val titulo: String = historico.titulo

    @Schema(description = "Instituição responsável pela certificação.", example = "Oracle")
    @JsonProperty("instituicao")
    val resumo: String = historico.instituicao

    @Schema(description = "Ano da certificação", example = "2017")
    @JsonProperty("ano")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).certificacao(id)).withSelfRel())
    }
}
