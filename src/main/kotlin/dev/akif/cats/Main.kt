package dev.akif.cats

import dev.akif.crud.common.InstantProvider
import dev.akif.crud.error.CRUDErrorHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@SpringBootApplication
class Main: CRUDErrorHandler {
    @Bean
    fun instantProvider(): InstantProvider = InstantProvider.utc
}

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}
