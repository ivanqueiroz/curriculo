package dev.ivanqueiroz.curriculo.rest.contato

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@Schema(description = "Classe que representa um meio de contato.")
class ContatoResource @JsonCreator constructor(@JsonIgnore val contato: Contato) : RepresentationModel<ContatoResource>() {

    @Schema(description = "Identificador único de contato.", example = "1", required = true)
    val id: Long = contato.id

    @Schema(description = "Valor do contato.", example = "+5571999999999, email@contato.com")
    val valor: String = contato.valor

    @Schema(description = "Tipos de contato.", example = "NENHUM, TELEFONE, EMAIL, SKYPE, FACEBOOK, TWITTER e LINKEDIN")
    val tipoContato: TipoContato = contato.tipoContato

    init {
        add(linkTo(methodOn(ContatoRestController::class.java).contato(id)).withSelfRel())
    }
}
