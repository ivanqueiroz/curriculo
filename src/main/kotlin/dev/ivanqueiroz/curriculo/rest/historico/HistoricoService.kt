package dev.ivanqueiroz.curriculo.rest.historico

import dev.ivanqueiroz.curriculo.dominio.historico.Historico
import dev.ivanqueiroz.curriculo.dominio.historico.HistoricoRepository
import dev.ivanqueiroz.curriculo.dominio.historico.TipoHistorico
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class HistoricoService {

    @Autowired
    lateinit var historicoRepository: HistoricoRepository


    fun obterTodasExperiencias(): List<ExperienciaResource> {
        val historicos = historicoRepository.findAllByTipoHistorico(TipoHistorico.EXPERIENCIA)
        val experiencias = historicos.map { historico -> ExperienciaResource(historico) }
        return experiencias
    }

    fun obterExperienciaPorId(id: Long): ExperienciaResource {
        return ExperienciaResource(historicoRepository.findByIdAndTipoHistorico(id, TipoHistorico.EXPERIENCIA).orElseThrow { RuntimeException("Registro não encontrado") })
    }

}