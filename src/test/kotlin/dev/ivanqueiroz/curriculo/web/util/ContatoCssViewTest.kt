package dev.ivanqueiroz.curriculo.web.util

import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ContatoCssViewTest {

    @BeforeEach
    fun before(){

    }

    @Test
    fun getClassCssIcone() {
    }

    @Test
    fun getMostraDescricao() {
    }

    @Test
    fun getValorFormatado() {
        val contato = Contato("5571987731477", "Telefone", TipoContato.TELEFONE)
        val contatoCssView = ContatoCssView(contato)
        assert(contatoCssView.valorFormatado == "+55 (71) 98773-1477")
    }

    @Test
    fun getDados() {
    }
}
