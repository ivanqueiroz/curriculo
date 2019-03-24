package dev.ivanqueiroz.curriculo.rest.contato

import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.ContatoRepository
import dev.ivanqueiroz.curriculo.dominio.contato.TipoContato
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ContatoService {

    @Autowired
    lateinit var contatoRepository: ContatoRepository

    fun obterTodosContatos(): List<ContatoResource>{
        return contatoRepository.findAll().map { c-> ContatoResource(c) }
    }

    fun obterContatoPorId(id: Long): ContatoResource {
        return ContatoResource(contatoRepository.findById(id).orElseThrow { RuntimeException("Registro não encontrado")})
    }

    fun obterContatoPorTipo(tipoContato: TipoContato): ContatoResource {
        return ContatoResource(contatoRepository.findByTipoContato(tipoContato).orElseThrow { RuntimeException("Registro não encontrado")})
    }

}
