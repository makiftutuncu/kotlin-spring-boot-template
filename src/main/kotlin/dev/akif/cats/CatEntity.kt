package dev.akif.cats

import dev.akif.crud.CRUDEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "cats")
class CatEntity(
    @Id override var id: UUID? = null,
    var name: String? = null,
    var breed: String? = null,
    var age: Int? = null,
    override var version: Int? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
    override var deletedAt: Instant? = null
) : CRUDEntity<UUID>(id, version, createdAt, updatedAt, deletedAt) {
    override fun toString(): String {
        return "CatEntity(id=$id, name=$name, breed=$breed, age=$age, version=$version, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
    }
}
