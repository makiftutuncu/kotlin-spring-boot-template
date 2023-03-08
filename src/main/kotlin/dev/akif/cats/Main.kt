package dev.akif.cats

import dev.akif.crud.common.InstantProvider
import dev.akif.crud.error.CRUDErrorHandler
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RestControllerAdvice

@OpenAPIDefinition(
    info = Info(
        title = "Cats API",
        version = "1.0.0",
        description = "An API for a cats, capable of managing cats and their toys",
        contact = Contact(name = "Mehmet Akif Tütüncü", url = "https://akif.dev"),
        license = License(
            name = "MIT License",
            url = "https://opensource.org/licenses/MIT"
        )
    )
)
@RestControllerAdvice
@SpringBootApplication
class Main : CRUDErrorHandler {
    @Bean
    fun instantProvider(): InstantProvider = InstantProvider.utc
}

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}
