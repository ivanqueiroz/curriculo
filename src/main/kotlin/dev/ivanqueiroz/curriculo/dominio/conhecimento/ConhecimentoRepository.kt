package dev.ivanqueiroz.curriculo.dominio.conhecimento

import org.springframework.data.jpa.repository.JpaRepository

interface ConhecimentoRepository : JpaRepository<Conhecimento, Long> {
    fun findByTituloContainingIgnoreCase(titulo: String): List<Conhecimento>

    fun findByTipoConhecimento(tipoConhecimento: TipoConhecimento): List<Conhecimento>

}
