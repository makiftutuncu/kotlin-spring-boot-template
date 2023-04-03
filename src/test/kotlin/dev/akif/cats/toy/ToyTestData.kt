package dev.akif.cats.toy

import dev.akif.cats.cat.CatTestData
import dev.akif.crud.CRUDTestData
import dev.akif.crud.IdGenerator
import dev.akif.crud.InMemoryCRUDRepository
import dev.akif.crud.common.Paged
import dev.akif.crud.common.Parameters
import org.springframework.data.domain.PageRequest

object ToyTestData : CRUDTestData<Long, ToyEntity, Toy, CreateToy, UpdateToy, ToyTestData>(typeName = "Toy") {
    override val repository: InMemoryCRUDRepository<Long, ToyEntity, CreateToy, ToyTestData>
        get() = InMemoryToyRepository

    override val idGenerator: IdGenerator<Long> =
        IdGenerator.sequential(0)

    override val testEntity1: ToyEntity =
        ToyEntity(
            id = idGenerator.next(),
            catId = CatTestData.testEntity1.id,
            name = "Mouse",
            version = 0,
            createdAt = now(),
            updatedAt = now(),
            deletedAt = null
        )

    override val testEntity2: ToyEntity =
        ToyEntity(
            id = idGenerator.next(),
            catId = CatTestData.testEntity1.id,
            name = "Yarn",
            version = 0,
            createdAt = now().plusSeconds(1),
            updatedAt = now().plusSeconds(1),
            deletedAt = null
        )

    override val testEntity3: ToyEntity =
        ToyEntity(
            id = idGenerator.next(),
            catId = CatTestData.testEntity2.id,
            name = "Ball",
            version = 0,
            createdAt = now().plusSeconds(2),
            updatedAt = now().plusSeconds(2),
            deletedAt = null
        )

    override val moreTestEntities: Array<ToyEntity> =
        emptyArray()

    override val defaultFirstPageEntities: List<ToyEntity> =
        listOf(
            testEntity1,
            testEntity2
        )

    override val paginationTestCases: List<Pair<PageRequest, Paged<ToyEntity>>> =
        listOf(
            PageRequest.of(0, 1) to Paged(
                data = listOf(testEntity1),
                page = 0,
                perPage = 1,
                totalPages = 2
            ),
            PageRequest.of(1, 1) to Paged(
                data = listOf(testEntity2),
                page = 1,
                perPage = 1,
                totalPages = 2
            ),
            PageRequest.of(2, 1) to Paged.empty(page = 2, perPage = 1, totalPages = 2)
        )

    override val testParameters: Parameters =
        Parameters(
            path = mapOf("catId" to CatTestData.testEntity1.id.toString()),
            query = emptyMap()
        )

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
