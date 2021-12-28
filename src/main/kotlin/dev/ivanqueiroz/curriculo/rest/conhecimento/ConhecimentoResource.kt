package dev.ivanqueiroz.curriculo.rest.conhecimento

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.conhecimento.Conhecimento
import dev.ivanqueiroz.curriculo.dominio.conhecimento.TipoConhecimento
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa um conhecimento adquirido.")
class ConhecimentoResource @JsonCreator constructor(@JsonIgnore val conhecimento: Conhecimento) : RepresentationModel<ConhecimentoResource>() {

    @Schema(description = "Identificador único de conhecimento.", example = "1", required = true)
    val id: Long = conhecimento.id

    @Schema(description = "Assunto do conhecimento.", example = "Java")
    @JsonProperty("assunto")
    val assunto: String = conhecimento.titulo

    @Schema(description = "Valor do nível de conhecimento. De 0.0 a 1.0.", example = "0.75")
    @JsonProperty("valorNivel")
    val valorNivel: Float = conhecimento.nivel

    @Schema(
        description = "Descrição baseado no nível e tipo do conhecimento.",
        example = "Iniciante, Proficiente, Especialista e Mestre para específicos. Básico, Intermediário e Avançado para línguas"
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
