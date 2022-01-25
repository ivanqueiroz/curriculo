package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa uma experiência profissional.")
data class ExperienciaResource(

    @Schema(description = "Identificador único de experiência.", example = "1", required = true)
    var id: Long = 0L,

    @Schema(description = "Nome da empresa trabalhada.", example = "Indra")
    @JsonProperty("empresa")
    val empresa: String,

    @Schema(description = "Cargo ocupado durante a experiência.", example = "Analista de Sistemas")
    @JsonProperty("cargo")
    val titulo: String,

    @Schema(description = "Resumo das atividades exercidas.", example = "Monitoria e suporte da infra de telefonia...")
    @JsonProperty("resumo")
    val resumo: String,

    @Schema(description = "Ano do início da experiência.", example = "2016")
    @JsonProperty("inicio")
    val anoInicio: String,

    @Schema(description = "Ano do fim da experiência.", example = "2017")
    @JsonProperty("fim")
    var anoFim: String = ""
) : RepresentationModel<ExperienciaResource>() {
    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).experiencia(id)).withSelfRel())
    }
}
