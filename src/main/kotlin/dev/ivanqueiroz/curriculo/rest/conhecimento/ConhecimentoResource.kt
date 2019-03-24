package dev.ivanqueiroz.curriculo.rest.conhecimento

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.conhecimento.Conhecimento
import dev.ivanqueiroz.curriculo.dominio.conhecimento.TipoConhecimento
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

@ApiModel(description = "Classe que representa um conhecimento adquirido.")
class ConhecimentoResource @JsonCreator constructor(@JsonIgnore val conhecimento: Conhecimento) : ResourceSupport() {

    @ApiModelProperty(notes = "Identificador único de conhecimento.", example = "1", required = true, position = 0)
    val id: Long = conhecimento.id

    @ApiModelProperty(notes = "Assunto do conhecimento.", example = "Java", position = 1)
    @JsonProperty("assunto")
    val assunto: String = conhecimento.titulo

    @ApiModelProperty(notes = "Valor do nível de conhecimento. De 0.0 a 1.0.", example = "0.75", position = 2)
    @JsonProperty("valorNivel")
    val valorNivel: Float = conhecimento.nivel

    @ApiModelProperty(notes = "Descrição baseado no nível e tipo do conhecimento.", example = "Iniciante, Proficiente, Especialista e Mestre para específicos. Básico, Intermediário e Avançado para línguas", position = 3)
    @JsonProperty("descricaoNivel")
    var descricaoNivel: String = ""
        get() {
            if (conhecimento.tipoConhecimento == TipoConhecimento.ESPECIFICO) {
                if (conhecimento.nivel < 0.25f) {
                    return "Iniciante"
                } else if (0.25f >= conhecimento.nivel && conhecimento.nivel < 0.75f) {
                    return "Proficiente"
                } else if (conhecimento.nivel >= 0.75f || conhecimento.nivel < 1.0f) {
                    return "Especialista"
                } else if (conhecimento.nivel == 1.0f) {
                    return "Mestre"
                } else {
                    return "Iniciante"
                }
            } else {
                if (conhecimento.nivel < 0.25f) {
                    return "Instrumental"
                } else if (0.25f >= conhecimento.nivel && conhecimento.nivel < 0.75f) {
                    return "Básico"
                } else if (conhecimento.nivel >= 0.75f || conhecimento.nivel < 1.0f) {
                    return "Intermediário"
                } else if (conhecimento.nivel == 1.0f) {
                    return "Avançado"
                } else {
                    return "Instrumental"
                }
            }
        }

    init {
        add(linkTo(methodOn(ConhecimentoRestController::class.java).conhecimento(id)).withSelfRel())
    }
}