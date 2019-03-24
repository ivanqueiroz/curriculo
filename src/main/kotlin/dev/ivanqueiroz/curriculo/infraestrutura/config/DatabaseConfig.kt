package dev.ivanqueiroz.curriculo.infraestrutura.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import com.zaxxer.hikari.HikariDataSource
import com.zaxxer.hikari.HikariConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import javax.sql.DataSource


@Configuration
@Profile("prod")
class DatabaseConfig {

    @Value("\${spring.datasource.url}")
    private val dbUrl: String? = null

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dbUrl
        return HikariDataSource(config)
    }
}
