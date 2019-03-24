package dev.ivanqueiroz.curriculo.rest.historico

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class ExperienciaResource @JsonCreator constructor(@JsonIgnore val historico: Historico) : ResourceSupport() {

    val id: Long = historico.id

    @JsonProperty("empresa")
    val empresa:String = historico.instituicao

    @JsonProperty("cargo")
    val titulo: String = historico.titulo

    @JsonProperty("resumo")
    val resumo: String = historico.descricao

    @JsonProperty("inicio")
    val anoInicio: String = historico.anoInicio

    @JsonProperty("fim")
    var anoFim: String =  historico.anoFim
    get() = if("" ==  historico.anoFim) "Atual" else  historico.anoFim

    init{
        add(linkTo(methodOn(HistoricoRestController::class.java).experiencia(id)).withSelfRel())
    }
}