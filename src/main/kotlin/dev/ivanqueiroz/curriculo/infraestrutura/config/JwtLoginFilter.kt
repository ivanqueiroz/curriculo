package dev.ivanqueiroz.curriculo.infraestrutura.config

import com.fasterxml.jackson.databind.ObjectMapper
import dev.ivanqueiroz.curriculo.dominio.usuario.UserDetail
import dev.ivanqueiroz.curriculo.infraestrutura.model.Credentials
import dev.ivanqueiroz.curriculo.util.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val (username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(username, password)
        return authManager.authenticate(token)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val user = (authResult?.principal as UserDetail)
        val token = jwtUtil.generateToken(user.username, user.authorities)
        response?.addHeader("Authorization", "Bearer $token")
    }
}

