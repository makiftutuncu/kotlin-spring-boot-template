package dev.akif.cats

import dev.akif.crud.CRUDTestData
import java.util.UUID

class CatTestData : CRUDTestData<UUID, CatEntity, Cat, CreateCat, UpdateCat, CatTestData>(typeName = "Cat") {
    private val catId1 = UUID.randomUUID()
    private val catId2 = UUID.randomUUID()
    private val catId3 = UUID.randomUUID()

    override val testEntity1: CatEntity =
        CatEntity(
            id = catId1,
            name = "Cookie",
            breed = "Tabby",
            age = 4,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override val testEntity2: CatEntity =
        CatEntity(
            id = catId2,
            name = "Kitty",
            breed = "Persian",
            age = 3,
            version = 0,
            createdAt = now.plusSeconds(1),
            updatedAt = now.plusSeconds(1),
            deletedAt = null
        )

    override val testEntity3: CatEntity =
        CatEntity(
            id = catId3,
            name = "Meowth",
            breed = "Scottish Fold",
            age = 2,
            version = 0,
            createdAt = now.plusSeconds(2),
            updatedAt = now.plusSeconds(2),
            deletedAt = null
        )

    override val moreTestEntities: Array<CatEntity> =
        emptyArray()

    override fun areDuplicates(e1: CatEntity, e2: CatEntity): Boolean =
        e1.name == e2.name
                && e1.breed == e2.breed
                && e1.age == e2.age

    override fun copy(entity: CatEntity): CatEntity =
        CatEntity(
            id = entity.id,
            name = entity.name,
            breed = entity.breed,
            age = entity.age,
            version = entity.version,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            deletedAt = entity.deletedAt
        )

    override fun randomId(): UUID =
        UUID.randomUUID()

    override fun entityToCreateModel(entity: CatEntity): CreateCat =
        CreateCat(
            name = entity.name!!,
            breed = entity.breed!!,
            age = entity.age!!
        )

    override fun entityToUpdateModelWithModifications(entity: CatEntity): UpdateCat =
        UpdateCat(
            name = "${entity.name}-updated",
            age = entity.age?.plus(1) ?: 1
        )

    override fun entityToUpdateModelWithNoModifications(entity: CatEntity): UpdateCat =
        UpdateCat(
            name = entity.name!!,
            age = entity.age!!
        )
}
