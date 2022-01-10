package dev.ivanqueiroz.curriculo.infraestrutura.config

import dev.ivanqueiroz.curriculo.util.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailService: UserDetailsService,
    private val jwtUtil: JwtUtil
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http
            ?.csrf()?.disable()
            ?.authorizeHttpRequests()
            ?.antMatchers("/conhecimentos")?.hasAnyAuthority("LEITURA_ESCRITA")
            ?.antMatchers(HttpMethod.POST, "/login")
            ?.permitAll()
            ?.anyRequest()
            ?.authenticated()
            ?.and()
        http?.addFilterBefore(JwtLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.addFilterBefore(JwtAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailService)?.passwordEncoder(bCryptPasswordEnconder())
    }

    @Bean
    fun bCryptPasswordEnconder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
