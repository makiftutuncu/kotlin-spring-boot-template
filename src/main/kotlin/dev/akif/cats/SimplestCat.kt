package dev.akif.cats

import dev.akif.crud.CRUDRepository
import dev.akif.crud.common.InstantProvider
import dev.akif.crud.simplest.*
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.persistence.Id
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.persistence.Entity as JakartaEntity
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/simplest-cats")
@Tag(name = "Simplest Cats", description = "CRUD operations for the simplest cat entities")
class SimplestCatController(service: SimplestCatService, mapper: SimplestCatMapper): SimplestController<UUID, SimplestCat, SimplestCatMapper, SimplestCatRepository, SimplestCatService>(
    typeName = "SimplestCat",
    service = service,
    mapper = mapper
)

@Service
class SimplestCatService(
    instantProvider: InstantProvider,
    crudRepository: CRUDRepository<UUID, SimplestCat>,
    mapper: SimplestCatMapper
): SimplestService<UUID, SimplestCat, SimplestCatRepository, SimplestCatMapper>(
    typeName = "SimplestCat",
    instantProvider = instantProvider,
    crudRepository = crudRepository,
    mapper = mapper
)

@Component
class SimplestCatMapper: SimplestMapper<UUID, SimplestCat> {
    override fun entityToBeCreatedFrom(createModel: SimplestCat, now: Instant): SimplestCat =
        SimplestCat(
            id = UUID.randomUUID(),
            name = createModel.name,
            breed = createModel.breed,
            age = createModel.age,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override fun entityToModel(entity: SimplestCat): SimplestCat =
        SimplestCat(
            id = requireNotNull(entity.id) { "SimplestCatEntity.id was null" },
            name = requireNotNull(entity.name) { "SimplestCatEntity.name was null" },
            breed = requireNotNull(entity.breed) { "SimplestCatEntity.breed was null" },
            age = requireNotNull(entity.age) { "SimplestCatEntity.age was null" },
            version = requireNotNull(entity.version) { "SimplestCatEntity.version was null" },
            createdAt = requireNotNull(entity.createdAt) { "SimplestCatEntity.createdAt was null" },
            updatedAt = requireNotNull(entity.updatedAt) { "SimplestCatEntity.updatedAt was null" },
            deletedAt = entity.deletedAt
        )

    override fun updateEntityWith(entity: SimplestCat, updateModel: SimplestCat) {
        entity.apply {
            name = updateModel.name
            breed = updateModel.breed
            age = updateModel.age
        }
    }

    override fun createDTOToCreateModel(createDTO: SimplestCat): SimplestCat =
        SimplestCat(
            id = createDTO.id,
            name = createDTO.name,
            breed = createDTO.breed,
            age = createDTO.age,
            version = 0,
            createdAt = createDTO.createdAt,
            updatedAt = createDTO.updatedAt,
            deletedAt = createDTO.deletedAt
        )

    override fun modelToDTO(model: SimplestCat): SimplestCat =
        SimplestCat(
            id = model.id,
            name = model.name,
            breed = model.breed,
            age = model.age,
            version = model.version,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt,
            deletedAt = model.deletedAt
        )

    override fun updateDTOToUpdateModel(updateDTO: SimplestCat): SimplestCat =
        SimplestCat(
            id = updateDTO.id,
            name = updateDTO.name,
            breed = updateDTO.breed,
            age = updateDTO.age,
            version = updateDTO.version,
            createdAt = updateDTO.createdAt,
            updatedAt = updateDTO.updatedAt,
            deletedAt = updateDTO.deletedAt
        )
}

@Repository
interface SimplestCatRepository: SimplestRepository<UUID, SimplestCat>

@JakartaEntity
class SimplestCat(
    @Id override var id: UUID?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): SimplestEntity<UUID, SimplestCat>(id, version, createdAt, updatedAt, deletedAt) {
    constructor() : this(
        id = null,
        name = null,
        breed = null,
        age = null,
        version = null,
        createdAt = null,
        updatedAt = null,
        deletedAt = null
    )
}
