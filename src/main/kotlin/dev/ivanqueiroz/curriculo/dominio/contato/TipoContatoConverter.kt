package dev.ivanqueiroz.curriculo.dominio.contato

import java.beans.PropertyEditorSupport

class TipoContatoConverter: PropertyEditorSupport() {
    @Throws(IllegalArgumentException::class)
    override fun setAsText(text: String) {
        value = TipoContato.valueOf(text.toUpperCase())
    }
}