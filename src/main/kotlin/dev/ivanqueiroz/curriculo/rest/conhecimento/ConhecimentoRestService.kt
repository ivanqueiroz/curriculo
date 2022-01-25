package dev.ivanqueiroz.curriculo.rest.conhecimento

import dev.ivanqueiroz.curriculo.dominio.conhecimento.Conhecimento
import dev.ivanqueiroz.curriculo.dominio.conhecimento.ConhecimentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConhecimentoRestService {

    @Autowired
    lateinit var conhecimentoRepository: ConhecimentoRepository

    fun obterTodosConhecimentos(): List<ConhecimentoResource> {
        return conhecimentoRepository.findAll().map { conhecimento ->
            ConhecimentoResource(
                id = conhecimento.id,
                tipoConhecimento = conhecimento.tipoConhecimento,
                assunto = conhecimento.titulo,
                valorNivel = conhecimento.nivel
            )
        }
    }

    fun obterConhecimentoPorId(id: Long): ConhecimentoResource {
        conhecimentoRepository.findById(id).orElseThrow { RuntimeException("Registro não encontrado") }.let { conhecimento ->
            return ConhecimentoResource(
                id = conhecimento.id,
                tipoConhecimento = conhecimento.tipoConhecimento,
                assunto = conhecimento.titulo,
                valorNivel = conhecimento.nivel
            )
        }

    }

    fun obterConhecimentoPeloAssunto(assunto: String): List<ConhecimentoResource> {
        return conhecimentoRepository.findByTituloContainingIgnoreCase(assunto).map { conhecimento ->
            ConhecimentoResource(
                id = conhecimento.id,
                tipoConhecimento = conhecimento.tipoConhecimento,
                assunto = conhecimento.titulo,
                valorNivel = conhecimento.nivel
            )
        }
    }

    fun inserirConhecimento(conhecimentoResource: ConhecimentoResource): ConhecimentoResource {
        val conhecimento = conhecimentoRepository.save(
            Conhecimento(
                titulo = conhecimentoResource.assunto,
                nivel = conhecimentoResource.valorNivel,
                tipoConhecimento = conhecimentoResource.tipoConhecimento
            )
        )
        return conhecimentoResource.copy(
            id = conhecimento.id
        )
    }

    fun atualizarConhecimento(conhecimentoResource: ConhecimentoResource): ConhecimentoResource {
        obterConhecimentoPorId(conhecimentoResource.id).let {
            conhecimentoRepository.save(
                Conhecimento(
                    id = conhecimentoResource.id,
                    titulo = conhecimentoResource.assunto,
                    nivel = conhecimentoResource.valorNivel,
                    tipoConhecimento = conhecimentoResource.tipoConhecimento
                )
            )
            return conhecimentoResource
        }
    }

    fun excluirConhecimento(id: Long) {
        obterConhecimentoPorId(id).let {
            conhecimentoRepository.deleteById(id)
        }
    }
}
