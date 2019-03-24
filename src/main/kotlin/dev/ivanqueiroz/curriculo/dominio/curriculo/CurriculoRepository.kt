package dev.ivanqueiroz.curriculo.dominio.curriculo

import org.springframework.data.jpa.repository.JpaRepository

interface CurriculoRepository: JpaRepository<Curriculo, Long>