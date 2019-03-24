package dev.ivanqueiroz.curriculo.rest.contato

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import dev.ivanqueiroz.curriculo.rest.historico.HistoricoRestController
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class ContatoResource @JsonCreator constructor(@JsonIgnore val contato: Contato) : ResourceSupport() {

    val id: Long = contato.id
    val valor: String = contato.valor
    val tipoContato: TipoContato = contato.tipoContato

    init {
        add(linkTo(methodOn(ContatoRestController::class.java).contato(id)).withSelfRel())
    }
}