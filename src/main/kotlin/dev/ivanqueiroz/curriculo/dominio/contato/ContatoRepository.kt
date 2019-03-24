package dev.ivanqueiroz.curriculo.dominio.contato

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ContatoRepository : JpaRepository<Contato, Long> {

    fun findByTipoContato(tipoContato: TipoContato): Optional<Contato>

    fun findByIdAndTipoContato(id: Long, tipoContato: TipoContato): Optional<Contato>
}