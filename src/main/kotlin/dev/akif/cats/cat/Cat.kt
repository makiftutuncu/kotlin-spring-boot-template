package dev.akif.cats.cat

import dev.akif.cats.toy.Toy
import dev.akif.crud.*
import java.time.Instant
import java.util.UUID

data class Cat(
    val id: UUID,
    val name: String,
    val breed: String,
    val age: Int,
    val toys: List<Toy>,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
) : CRUDModel<UUID> {
    override fun id(): UUID = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}

data class CreateCat(val name: String, val breed: String, val age: Int) : CRUDCreateModel

data class UpdateCat(val name: String, val age: Int) : CRUDUpdateModel
