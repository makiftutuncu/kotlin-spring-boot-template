package dev.akif.cats.toy

import dev.akif.crud.*
import java.time.Instant
import java.util.UUID

data class Toy(
    val id: Long,
    val catId: UUID,
    val name: String,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
) : CRUDModel<Long> {
    override fun id(): Long = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}

data class CreateToy(val catId: UUID, val name: String) : CRUDCreateModel

data class UpdateToy(val catId: UUID, val name: String) : CRUDUpdateModel
