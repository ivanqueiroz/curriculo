package dev.ivanqueiroz.curriculo.dominio.usuario

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = usuarioRepository.findUsuarioByLogin(username) ?: throw RuntimeException("Usuário não encontrado!")
        return UserDetail(usuario)
    }

}
