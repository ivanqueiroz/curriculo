package dev.ivanqueiroz.curriculo.util

import dev.ivanqueiroz.curriculo.dominio.role.Role
import dev.ivanqueiroz.curriculo.dominio.usuario.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(
    private val usuarioService: UsuarioService
) {

    private val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(user: String, authorities: List<Role>): String? {
        return Jwts.builder()
            .setSubject(user)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(jwtToken: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwtToken)
            true
        } catch (e: IllegalArgumentException) {
            false
        } catch (e: MalformedJwtException) {
            false
        } catch (e: UnsupportedJwtException) {
            false
        }

    }

    fun getAuthentication(jwtToken: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwtToken).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}
