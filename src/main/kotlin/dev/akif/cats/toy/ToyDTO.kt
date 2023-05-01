package dev.akif.cats.toy

import dev.akif.crud.*
import java.time.Instant

data class ToyDTO(
    val id: Long,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant
) : CRUDDTO<Long> {
    override fun id(): Long = id
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
}

data class CreateToyDTO(val name: String) : CRUDCreateDTO

data class UpdateToyDTO(val name: String) : CRUDUpdateDTO
