package dev.ivanqueiroz.curriculo.rest.contato

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

@ApiModel(description = "Classe que representa um meio de contato.")
class ContatoResource @JsonCreator constructor(@JsonIgnore val contato: Contato) : RepresentationModel<ContatoResource>() {

    @ApiModelProperty(notes = "Identificador único de contato.", example = "1", required = true, position = 0)
    val id: Long = contato.id

    @ApiModelProperty(notes = "Valor do contato.", example = "+5571999999999, email@contato.com", position = 1)
    val valor: String = contato.valor

    @ApiModelProperty(notes = "Tipos de contato.", example = "NENHUM, TELEFONE, EMAIL, SKYPE, FACEBOOK, TWITTER e LINKEDIN", position = 2)
    val tipoContato: TipoContato = contato.tipoContato

    init {
        add(linkTo(methodOn(ContatoRestController::class.java).contato(id)).withSelfRel())
    }
}
