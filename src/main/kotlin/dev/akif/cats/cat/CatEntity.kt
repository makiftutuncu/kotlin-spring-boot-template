package dev.akif.cats.cat

import dev.akif.cats.toy.ToyEntity
import dev.akif.crud.CRUDEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "cats")
class CatEntity(
    @Id
    override var id: UUID?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
) : CRUDEntity<UUID>(id, version, createdAt, updatedAt, deletedAt) {
    @JoinColumn(name = "cat_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToMany(cascade = [CascadeType.ALL])
    var toys: List<ToyEntity> = mutableListOf()

    override fun toString(): String {
        return "CatEntity(id=$id, name=$name, breed=$breed, age=$age, toys=$toys, version=$version, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
    }
}
