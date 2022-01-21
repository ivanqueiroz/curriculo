package dev.ivanqueiroz.curriculo.dominio.usuario

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findUsuarioByLogin(login: String?): Usuario?
}
