package dev.akif.cats.cat

import dev.akif.crud.CRUDTestData
import dev.akif.crud.IdGenerator
import dev.akif.crud.InMemoryCRUDRepository
import dev.akif.crud.common.Paged
import dev.akif.crud.common.Parameters
import org.springframework.data.domain.PageRequest
import java.util.UUID

object CatTestData : CRUDTestData<UUID, CatEntity, Cat, CreateCat, UpdateCat, CatTestData>(typeName = "Cat") {
    override val repository: InMemoryCRUDRepository<UUID, CatEntity, CreateCat, CatTestData>
        get() = InMemoryCatRepository

    override val idGenerator: IdGenerator<UUID> =
        IdGenerator.uuid

    private val catId1 = idGenerator.next()
    private val catId2 = idGenerator.next()
    private val catId3 = idGenerator.next()

    override val testEntity1: CatEntity =
        CatEntity(
            id = catId1,
            name = "Cookie",
            breed = "Tabby",
            age = 4,
            version = 0,
            createdAt = now(),
            updatedAt = now(),
            deletedAt = null
        )

    override val testEntity2: CatEntity =
        CatEntity(
            id = catId2,
            name = "Kitty",
            breed = "Persian",
            age = 3,
            version = 0,
            createdAt = now().plusSeconds(1),
            updatedAt = now().plusSeconds(1),
            deletedAt = null
        )

    override val testEntity3: CatEntity =
        CatEntity(
            id = catId3,
            name = "Meowth",
            breed = "Scottish Fold",
            age = 2,
            version = 0,
            createdAt = now().plusSeconds(2),
            updatedAt = now().plusSeconds(2),
            deletedAt = null
        )

    override val defaultFirstPageEntities: List<CatEntity> =
        listOf(
            testEntity1,
            testEntity2,
            testEntity3
        )

    override val paginationTestCases: List<Pair<PageRequest, Paged<CatEntity>>> =
        listOf(
            PageRequest.of(0, 2) to Paged(
                data = listOf(testEntity1, testEntity2),
                page = 0,
                perPage = 1,
                totalPages = 2
            ),
            PageRequest.of(1, 2) to Paged(
                data = listOf(testEntity3),
                page = 1,
                perPage = 1,
                totalPages = 2
            ),
            PageRequest.of(2, 2) to Paged.empty(page = 2, perPage = 2, totalPages = 2)
        )

    override val moreTestEntities: Array<CatEntity> =
        emptyArray()

    override val testParameters: Parameters =
        Parameters.empty

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
