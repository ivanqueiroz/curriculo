package dev.ivanqueiroz.curriculo.rest.conhecimento

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.conhecimento.Conhecimento
import dev.ivanqueiroz.curriculo.dominio.conhecimento.TipoConhecimento
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@ApiModel(description = "Classe que representa um conhecimento adquirido.")
class ConhecimentoResource @JsonCreator constructor(@JsonIgnore val conhecimento: Conhecimento) : RepresentationModel<ConhecimentoResource>() {

    @ApiModelProperty(notes = "Identificador único de conhecimento.", example = "1", required = true, position = 0)
    val id: Long = conhecimento.id

    @ApiModelProperty(notes = "Assunto do conhecimento.", example = "Java", position = 1)
    @JsonProperty("assunto")
    val assunto: String = conhecimento.titulo

    @ApiModelProperty(notes = "Valor do nível de conhecimento. De 0.0 a 1.0.", example = "0.75", position = 2)
    @JsonProperty("valorNivel")
    val valorNivel: Float = conhecimento.nivel

    @ApiModelProperty(
        notes = "Descrição baseado no nível e tipo do conhecimento.",
        example = "Iniciante, Proficiente, Especialista e Mestre para específicos. Básico, Intermediário e Avançado para línguas",
        position = 3
    )
    @JsonProperty("descricaoNivel")
    var descricaoNivel: String = ""
        get() {
            if (conhecimento.tipoConhecimento == TipoConhecimento.ESPECIFICO) {
                return if (conhecimento.nivel < 0.25f) {
                    "Iniciante"
                } else if (0.25f >= conhecimento.nivel && conhecimento.nivel < 0.75f) {
                    "Proficiente"
                } else if (conhecimento.nivel >= 0.75f || conhecimento.nivel < 1.0f) {
                    "Especialista"
                } else if (conhecimento.nivel == 1.0f) {
                    "Mestre"
                } else {
                    "Iniciante"
                }
            } else {
                return if (conhecimento.nivel < 0.25f) {
                    "Instrumental"
                } else if (0.25f >= conhecimento.nivel && conhecimento.nivel < 0.75f) {
                    "Básico"
                } else if (conhecimento.nivel >= 0.75f || conhecimento.nivel < 1.0f) {
                    "Intermediário"
                } else if (conhecimento.nivel == 1.0f) {
                    "Avançado"
                } else {
                    "Instrumental"
                }
            }
        }

    init {
        add(linkTo(methodOn(ConhecimentoRestController::class.java).conhecimento(id)).withSelfRel())
    }
}
