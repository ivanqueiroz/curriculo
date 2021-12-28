package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa um treinamento realizado.")
class TreinamentoResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<TreinamentoResource>() {

    @Schema(description = "Identificador único do treinamento.", example = "1", required = true)
    val id: Long = historico.id

    @Schema(description = "Local onde foi realizado o treinamento.", example = "UCSAL")
    @JsonProperty("local")
    val empresa: String = historico.instituicao

    @Schema(description = "Cargo ocupado durante a experiência.", example = "Analista de Sistemas")
    @JsonProperty("cargo")
    val titulo: String = historico.titulo

    @Schema(description = "Resumo das atividades exercidas.", example = "Monitoria e suporte da infra de telefonia...")
    @JsonProperty("resumo")
    val resumo: String = historico.descricao

    @Schema(description = "Ano do treinamento.", example = "2017")
    @JsonProperty("ano")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).treinamento(id)).withSelfRel())
    }

}
