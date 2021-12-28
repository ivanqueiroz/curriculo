package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa uma experiência profissional.")
class ExperienciaResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<ExperienciaResource>() {

    @Schema(description = "Identificador único de experiência.", example = "1", required = true)
    val id: Long = historico.id

    @Schema(description = "Nome da empresa trabalhada.", example = "Indra")
    @JsonProperty("empresa")
    val empresa: String = historico.instituicao

    @Schema(description = "Cargo ocupado durante a experiência.", example = "Analista de Sistemas")
    @JsonProperty("cargo")
    val titulo: String = historico.titulo

    @Schema(description = "Resumo das atividades exercidas.", example = "Monitoria e suporte da infra de telefonia...")
    @JsonProperty("resumo")
    val resumo: String = historico.descricao

    @Schema(description = "Ano do início da experiência.", example = "2016")
    @JsonProperty("inicio")
    val anoInicio: String = historico.anoInicio

    @Schema(description = "Ano do fim da experiência.", example = "2017")
    @JsonProperty("fim")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).experiencia(id)).withSelfRel())
    }
}
