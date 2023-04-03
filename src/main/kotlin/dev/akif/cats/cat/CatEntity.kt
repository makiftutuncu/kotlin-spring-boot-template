package dev.akif.cats.cat

import dev.akif.cats.toy.ToyEntity
import dev.akif.crud.CRUDEntity
import jakarta.persistence.*
import org.hibernate.annotations.Where
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
) : CRUDEntity<UUID>() {
    @JoinColumn(name = "catId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToMany(cascade = [CascadeType.ALL])
    @Where(clause = "deleted_at IS NULL")
    var toys: List<ToyEntity> = mutableListOf()

    override fun toString(): String =
        "CatEntity(id=$id, name=$name, breed=$breed, age=$age, toys=$toys, version=$version, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
}
