package dev.ivanqueiroz.curriculo.rest.conhecimento

import dev.ivanqueiroz.curriculo.dominio.conhecimento.ConhecimentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ConhecimentoRestService {

    @Autowired
    lateinit var conhecimentoRepository: ConhecimentoRepository

    fun obterTodosConhecimentos(): List<ConhecimentoResource> {
        return conhecimentoRepository.findAll().map { c -> ConhecimentoResource(c) }
    }

    fun obterConhecimentoPorId(id: Long): ConhecimentoResource {
        return ConhecimentoResource(conhecimentoRepository.findById(id).orElseThrow { RuntimeException("Registro não encontrado") })
    }

    fun obterConhecimentoPeloAssunto(assunto: String): List<ConhecimentoResource> {
        return conhecimentoRepository.findByTituloContainingIgnoreCase(assunto).map { c -> ConhecimentoResource(c) }
    }
}