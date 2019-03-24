package dev.ivanqueiroz.curriculo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CurriculoApplication

fun main(args: Array<String>) {
    runApplication<CurriculoApplication>(*args)
}
