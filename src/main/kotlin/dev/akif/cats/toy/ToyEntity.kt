package dev.akif.cats.toy

import dev.akif.crud.CRUDEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "toys")
class ToyEntity(
    @Id
    override var id: UUID?,
    @Column(name = "cat_id")
    var catId: UUID?,
    var name: String?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
) : CRUDEntity<UUID>(id, version, createdAt, updatedAt, deletedAt) {
    override fun toString(): String {
        return "ToyEntity(id=$id, catId=$catId, name=$name, version=$version, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
    }
}
