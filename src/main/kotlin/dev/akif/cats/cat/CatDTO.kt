package dev.akif.cats.cat

import dev.akif.cats.toy.ToyDTO
import dev.akif.crud.*
import java.time.Instant
import java.util.UUID

data class CatDTO(
    val id: UUID,
    val name: String,
    val breed: String,
    val age: Int,
    val toys: List<ToyDTO>,
    val createdAt: Instant,
    val updatedAt: Instant
) : CRUDDTO<UUID> {
    override fun id(): UUID = id
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
}

data class CreateCatDTO(val name: String, val breed: String, val age: Int) : CRUDCreateDTO

data class UpdateCatDTO(val name: String, val age: Int) : CRUDUpdateDTO
