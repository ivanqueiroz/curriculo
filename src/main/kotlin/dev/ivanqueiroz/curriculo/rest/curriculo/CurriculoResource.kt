package dev.ivanqueiroz.curriculo.rest.curriculo

import dev.ivanqueiroz.curriculo.dominio.curriculo.Curriculo
import dev.ivanqueiroz.curriculo.rest.historico.HistoricoRestController
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo


class CurriculoResource(val curriculo: Curriculo) : ResourceSupport() {

    val id: Long = curriculo.id

    val primeiroNome: String = curriculo.primeiroNome
    val ultimoNome: String = curriculo.ultimoNome
    val profissao: String = curriculo.profissao

    init{
        add(linkTo(HistoricoRestController::class).withRel("curriculo"))
    }

}
