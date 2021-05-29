package dev.ivanqueiroz.curriculo.web.curriculo

import dev.ivanqueiroz.curriculo.dominio.conhecimento.Conhecimento
import dev.ivanqueiroz.curriculo.dominio.conhecimento.ConhecimentoRepository
import dev.ivanqueiroz.curriculo.dominio.conhecimento.TipoConhecimento
import dev.ivanqueiroz.curriculo.dominio.contato.Contato
import dev.ivanqueiroz.curriculo.dominio.contato.ContatoRepository
import dev.ivanqueiroz.curriculo.dominio.curriculo.Curriculo
import dev.ivanqueiroz.curriculo.dominio.curriculo.CurriculoRepository
import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import dev.ivanqueiroz.curriculo.dominio.historico.HistoricoRepository
import dev.ivanqueiroz.curriculo.dominio.historico.TipoHistorico
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.stream.Collectors

@Service
class CurriculoService {

    @Autowired
    lateinit var curriculoRepository: CurriculoRepository

    @Autowired
    lateinit var historicoRepository: HistoricoRepository

    @Autowired
    lateinit var conhecimentoRepository: ConhecimentoRepository

    @Autowired
    lateinit var contatoRepository: ContatoRepository

    fun obterCurriculoPortugues(): Curriculo {
        return curriculoRepository.findById(1L).orElseThrow { RuntimeException("Curriculo não encontrado!") };
    }

    fun obterHistoricoFormacao(): List<Historico> {
        return obterHistoricoPorTipo(TipoHistorico.EDUCACAO)
    }

    fun obterHistoricoCertificacao(): List<Historico> {
        return obterHistoricoPorTipo(TipoHistorico.CERTIFICACOES)
    }

    fun obterHistoricoExperiencia(): List<Historico> {
        return historicoRepository.findAllByTipoHistoricoOrderByAnoInicioDesc(TipoHistorico.EXPERIENCIA)
    }

    fun obterHistoricoPalestras(): List<Historico> {
        return historicoRepository.findAllByTipoHistoricoOrderByAnoInicioDesc(TipoHistorico.PALESTRAS_APLICADAS).stream().sorted { h1, h2 -> h1.anoFim.compareTo(h2.anoFim) }.collect(Collectors.toList())
    }

    fun obterHistoricoTreinamentos(): List<Historico> {
        return historicoRepository.findAllByTipoHistoricoOrderByAnoInicioDesc(TipoHistorico.CURSOS_APLICADOS)
    }

    private fun obterHistoricoPorTipo(tipoHistorico: TipoHistorico): List<Historico> {
        return historicoRepository.findAllByTipoHistorico(tipoHistorico).stream().sorted { h1, h2 -> h1.anoFim.compareTo(h2.anoFim) }.collect(Collectors.toList())
    }

    fun obterContatos(): List<Contato> {
        return contatoRepository.findAll();
    }

    fun obterConhecimentosEspecificos(): List<Conhecimento> {
        return conhecimentoRepository.findByTipoConhecimento(TipoConhecimento.ESPECIFICO)
    }

    fun obterConhecimentosLinguas(): List<Conhecimento> {
        return conhecimentoRepository.findByTipoConhecimento(TipoConhecimento.LINGUAS)
    }
}
