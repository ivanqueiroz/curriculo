package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa uma formação realizada ou em andamento.")
class FormacaoResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<FormacaoResource>() {

    @Schema(description = "Identificador único da formação.", example = "1", required = true)
    val id: Long = historico.id

    @Schema(description = "Título da formação.", example = "Bacharelado em Sistemas de Informação")
    @JsonProperty("formacao")
    val titulo: String = historico.titulo

    @Schema(description = "Detalhamento da formação.", example = "Completado em junho de 2012 no Centro Universitário Estácio da Bahia")
    @JsonProperty("resumo")
    val resumo: String = historico.descricao

    @Schema(description = "Ano de conclusão.", example = "2017")
    @JsonProperty("conclusao")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).formacao(id)).withSelfRel())
    }
}
