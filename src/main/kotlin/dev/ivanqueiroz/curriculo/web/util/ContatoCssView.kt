package dev.ivanqueiroz.curriculo.web.util

import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato

data class ContatoCssView(val dados: Contato) {

    val classCssIcone: String = when (dados.tipoContato) {
        TipoContato.TELEFONE -> "fa fa-phone fa-fw"
        TipoContato.EMAIL -> "fa fa-envelope fa-fw"
        TipoContato.TWITTER -> "fa fa-twitter fa-fw"
        TipoContato.FACEBOOK -> "fa fa-facebook fa-fw"
        TipoContato.SKYPE -> "fa fa-skype fa-fw"
        TipoContato.LINKEDIN -> "fa fa-linkedin fa-fw"
        else -> {
            "fa fa-fw"
        }
    }

    val mostraDescricao: Boolean = dados.tipoContato != TipoContato.TELEFONE && dados.tipoContato != TipoContato.EMAIL

    val valorFormatado = when (dados.tipoContato) {
        TipoContato.TELEFONE -> dados.valor.replaceFirst(Regex("(\\d{2})(\\d{2})(\\d{5})(\\d+)"), "+$1 ($2) $3-$4")
        else -> dados.valor
    }
}
