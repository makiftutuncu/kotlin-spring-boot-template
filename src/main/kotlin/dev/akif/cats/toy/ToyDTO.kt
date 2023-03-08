package dev.akif.cats.toy

import dev.akif.crud.*
import java.time.Instant
import java.util.UUID

data class ToyDTO(
    val id: UUID,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant
) : CRUDDTO<UUID> {
    override fun id(): UUID = id
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
}

data class CreateToyDTO(val name: String) : CRUDCreateDTO

data class UpdateToyDTO(val name: String) : CRUDUpdateDTO
