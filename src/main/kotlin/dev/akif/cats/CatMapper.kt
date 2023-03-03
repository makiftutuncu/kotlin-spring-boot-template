package dev.akif.cats

import dev.akif.crud.CRUDMapper
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

@Component
class CatMapper : CRUDMapper<UUID, CatEntity, Cat, CreateCat, UpdateCat> {
    override fun entityToBeCreatedFrom(createModel: CreateCat, now: Instant): CatEntity =
        CatEntity(
            id = UUID.randomUUID(),
            name = createModel.name,
            breed = createModel.breed,
            age = createModel.age,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override fun entityToModel(entity: CatEntity): Cat =
        Cat(
            id = requireNotNull(entity.id) { "CatEntity.id was null" },
            name = requireNotNull(entity.name) { "CatEntity.name was null" },
            breed = requireNotNull(entity.breed) { "CatEntity.breed was null" },
            age = requireNotNull(entity.age) { "CatEntity.age was null" },
            version = requireNotNull(entity.version) { "CatEntity.version was null" },
            createdAt = requireNotNull(entity.createdAt) { "CatEntity.createdAt was null" },
            updatedAt = requireNotNull(entity.updatedAt) { "CatEntity.updatedAt was null" },
            deletedAt = entity.deletedAt
        )

    override fun updateEntityWith(entity: CatEntity, updateModel: UpdateCat) {
        entity.apply {
            name = updateModel.name
            age = updateModel.age
        }
    }
}
