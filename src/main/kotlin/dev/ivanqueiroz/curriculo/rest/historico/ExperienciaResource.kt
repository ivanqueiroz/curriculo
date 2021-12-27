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

@ApiModel(description = "Classe que representa uma experiência profissional.")
class ExperienciaResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : RepresentationModel<ExperienciaResource>() {

    @ApiModelProperty(notes = "Identificador único de experiência.", example = "1", required = true, position = 0)
    val id: Long = historico.id

    @ApiModelProperty(notes = "Nome da empresa trabalhada.", example = "Indra", position = 1)
    @JsonProperty("empresa")
    val empresa: String = historico.instituicao

    @ApiModelProperty(notes = "Cargo ocupado durante a experiência.", example = "Analista de Sistemas", position = 2)
    @JsonProperty("cargo")
    val titulo: String = historico.titulo

    @ApiModelProperty(notes = "Resumo das atividades exercidas.", example = "Monitoria e suporte da infra de telefonia...", position = 3)
    @JsonProperty("resumo")
    val resumo: String = historico.descricao

    @ApiModelProperty(notes = "Ano do início da experiência.", example = "2016", position = 4)
    @JsonProperty("inicio")
    val anoInicio: String = historico.anoInicio

    @ApiModelProperty(notes = "Ano do fim da experiência.", example = "2017", position = 5)
    @JsonProperty("fim")
    var anoFim: String = historico.anoFim
        get() = if ("" == historico.anoFim) "Atual" else historico.anoFim

    init {
        add(linkTo(methodOn(HistoricoRestController::class.java).experiencia(id)).withSelfRel())
    }
}
