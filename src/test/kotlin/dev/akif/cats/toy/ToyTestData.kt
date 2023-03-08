package dev.akif.cats.toy

import dev.akif.crud.CRUDTestData
import java.util.*

class ToyTestData : CRUDTestData<UUID, ToyEntity, Toy, CreateToy, UpdateToy, ToyTestData>(typeName = "Toy") {
    override val testEntity1: ToyEntity =
        ToyEntity(
            id = randomId(),
            catId = null,
            name = "Mouse",
            version = 0,
            createdAt = now(),
            updatedAt = now(),
            deletedAt = null
        )

    override val testEntity2: ToyEntity =
        ToyEntity(
            id = randomId(),
            catId = null,
            name = "Yarn",
            version = 0,
            createdAt = now().plusSeconds(1),
            updatedAt = now().plusSeconds(1),
            deletedAt = null
        )

    override val testEntity3: ToyEntity =
        ToyEntity(
            id = randomId(),
            catId = null,
            name = "Ball",
            version = 0,
            createdAt = now().plusSeconds(2),
            updatedAt = now().plusSeconds(2),
            deletedAt = null
        )

    override val moreTestEntities: Array<ToyEntity> =
        emptyArray()

    override fun areDuplicates(e1: ToyEntity, e2: ToyEntity): Boolean =
        e1.catId == e2.catId
                && e1.name == e2.name

    override fun copy(entity: ToyEntity): ToyEntity =
        ToyEntity(
            id = entity.id,
            catId = entity.catId,
            name = entity.name,
            version = entity.version,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            deletedAt = entity.deletedAt
        )

    override fun randomId(): UUID =
        UUID.randomUUID()

    override fun entityToCreateModel(entity: ToyEntity): CreateToy =
        CreateToy(
            catId = entity.catId!!,
            name = entity.name!!
        )

    override fun entityToUpdateModelWithModifications(entity: ToyEntity): UpdateToy =
        UpdateToy(
            catId = entity.catId!!,
            name = "${entity.name}-updated"
        )

    override fun entityToUpdateModelWithNoModifications(entity: ToyEntity): UpdateToy =
        UpdateToy(
            catId = entity.catId!!,
            name = entity.name!!
        )
}
