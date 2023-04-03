package dev.akif.cats.toy

import dev.akif.crud.CRUDEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "toys")
class ToyEntity(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    override var id: Long?,
    var catId: UUID?,
    var name: String?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
) : CRUDEntity<Long>() {
    override fun toString(): String =
        "ToyEntity(id=$id, catId=$catId, name=$name, version=$version, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
}
