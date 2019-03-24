package dev.ivanqueiroz.curriculo.rest.conhecimento

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.conhecimento.Conhecimento
import dev.ivanqueiroz.curriculo.dominio.conhecimento.TipoConhecimento
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class ConhecimentoResource @JsonCreator constructor(@JsonIgnore val conhecimento: Conhecimento) : ResourceSupport() {

    val id: Long = conhecimento.id

    @JsonProperty("assunto")
    val assunto: String = conhecimento.titulo

    @JsonProperty("valorNivel")
    val valorNivel: Float = conhecimento.nivel

    @JsonProperty("descricaoNivel")
    var descricaoNivel: String = ""
        get() {
            if(conhecimento.tipoConhecimento == TipoConhecimento.ESPECIFICO) {
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
            }else{
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