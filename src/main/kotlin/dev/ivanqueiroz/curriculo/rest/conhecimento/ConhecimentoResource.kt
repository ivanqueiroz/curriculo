package dev.ivanqueiroz.curriculo.rest.conhecimento

import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.conhecimento.TipoConhecimento
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa um conhecimento adquirido.")
data class ConhecimentoResource(

    @Schema(description = "Identificador único de conhecimento.", example = "1", required = true)
    val id: Long = 0L,

    @Schema(description = "Assunto do conhecimento.", example = "Java")
    @JsonProperty("assunto")
    val assunto: String = "",

    @Schema(description = "Valor do nível de conhecimento. De 0.0 a 1.0.", example = "0.75")
    @JsonProperty("valorNivel")
    val valorNivel: Float = 0F,

    @Schema(description = "Tipo de conhecimento.", example = "ESPECIFICO, LINGUAS")
    val tipoConhecimento: TipoConhecimento
) : RepresentationModel<ConhecimentoResource>() {

    @Schema(
        description = "Descrição baseado no nível e tipo do conhecimento.",
        example = "Iniciante, Proficiente, Especialista e Mestre para específicos. Básico, Intermediário e Avançado para línguas"
    )
    @JsonProperty("descricaoNivel")
    var descricaoNivel: String = ""
        get() {
            if (tipoConhecimento == TipoConhecimento.ESPECIFICO) {
                return if (valorNivel < 0.25f) {
                    "Iniciante"
                } else if (0.25f >= valorNivel && valorNivel < 0.75f) {
                    "Proficiente"
                } else if (valorNivel >= 0.75f || valorNivel < 1.0f) {
                    "Especialista"
                } else if (valorNivel == 1.0f) {
                    "Mestre"
                } else {
                    "Iniciante"
                }
            } else {
                return if (valorNivel < 0.25f) {
                    "Instrumental"
                } else if (0.25f >= valorNivel && valorNivel < 0.75f) {
                    "Básico"
                } else if (valorNivel >= 0.75f || valorNivel < 1.0f) {
                    "Intermediário"
                } else if (valorNivel == 1.0f) {
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
