package dev.ivanqueiroz.curriculo.rest.curriculo

import dev.ivanqueiroz.curriculo.dominio.conhecimento.ConhecimentoRepository
import dev.ivanqueiroz.curriculo.dominio.contato.ContatoRepository
import dev.ivanqueiroz.curriculo.dominio.curriculo.CurriculoRepository
import dev.ivanqueiroz.curriculo.dominio.historico.HistoricoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CurriculoRestService {

    @Autowired
    lateinit var curriculoRepository: CurriculoRepository

    @Autowired
    lateinit var historicoRepository: HistoricoRepository

    @Autowired
    lateinit var conhecimentoRepository: ConhecimentoRepository

    @Autowired
    lateinit var contatoRepository: ContatoRepository

    fun obterCurriculoPortugues(): CurriculoResource {

        val curriculo = curriculoRepository.findById(1L).orElseThrow { RuntimeException("Curriculo não encontrado!") }
        curriculo.historicos = historicoRepository.findAll()
        curriculo.conhecimentos = conhecimentoRepository.findAll()
        curriculo.contatos = contatoRepository.findAll()

        return CurriculoResource(curriculo);
    }

}