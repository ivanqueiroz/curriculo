package dev.ivanqueiroz.curriculo.rest.historico

import dev.ivanqueiroz.curriculo.dominio.historico.HistoricoRepository
import dev.ivanqueiroz.curriculo.dominio.historico.TipoHistorico
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HistoricoRestService {

    @Autowired
    private lateinit var historicoRepository: HistoricoRepository

    fun obterTodasExperiencias(): List<ExperienciaResource> {
        val historicos = historicoRepository.findAllByTipoHistorico(TipoHistorico.EXPERIENCIA)
        val experiencias = historicos.map { historico -> ExperienciaResource(historico) }
        return experiencias
    }

    fun obterExperienciaPorId(id: Long): ExperienciaResource {
        return ExperienciaResource(historicoRepository.findByIdAndTipoHistorico(id, TipoHistorico.EXPERIENCIA).orElseThrow { RuntimeException("Registro não encontrado") })
    }

    fun obterTodasCertificacoes(): List<CertificacaoResource> {
        val historicos = historicoRepository.findAllByTipoHistorico(TipoHistorico.CERTIFICACOES)
        val certificacoes = historicos.map { historico -> CertificacaoResource(historico) }
        return certificacoes
    }

    fun obterCertificacaoPorId(id: Long): CertificacaoResource {
        return CertificacaoResource(historicoRepository.findByIdAndTipoHistorico(id, TipoHistorico.CERTIFICACOES).orElseThrow { RuntimeException("Registro não encontrado") })
    }

    fun obterTodasFormacoes(): List<FormacaoResource> {
        val historicos = historicoRepository.findAllByTipoHistorico(TipoHistorico.EDUCACAO)
        val formacoes = historicos.map { historico -> FormacaoResource(historico) }
        return formacoes
    }

    fun obterFormacaoPorId(id: Long): FormacaoResource {
        return FormacaoResource(historicoRepository.findByIdAndTipoHistorico(id, TipoHistorico.EDUCACAO).orElseThrow { RuntimeException("Registro não encontrado") })
    }

    fun obterTodasPalestras(): List<PalestraResource> {
        val historicos = historicoRepository.findAllByTipoHistorico(TipoHistorico.PALESTRAS_APLICADAS)
        val palestras = historicos.map { historico -> PalestraResource(historico) }
        return palestras
    }

    fun obterPalestraPorId(id: Long): PalestraResource {
        return PalestraResource(historicoRepository.findByIdAndTipoHistorico(id, TipoHistorico.PALESTRAS_APLICADAS).orElseThrow { RuntimeException("Registro não encontrado") })
    }

    fun obterTodosTreinamentos(): List<TreinamentoResource> {
        val historicos = historicoRepository.findAllByTipoHistorico(TipoHistorico.CURSOS_APLICADOS)
        val treinamentos = historicos.map { historico -> TreinamentoResource(historico) }
        return treinamentos
    }

    fun obterTreinamentoPorId(id: Long): TreinamentoResource {
        return TreinamentoResource(historicoRepository.findByIdAndTipoHistorico(id, TipoHistorico.CURSOS_APLICADOS).orElseThrow { RuntimeException("Registro não encontrado") })
    }

    fun inserirExperiencia(experienciaResource: ExperienciaResource) {
        experienciaResource.let {

        }
    }
}
