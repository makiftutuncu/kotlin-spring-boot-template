package dev.akif.cats

import dev.akif.crud.CRUDRepository
import dev.akif.crud.common.InstantProvider
import dev.akif.crud.simpler.*
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
@RequestMapping("/simpler-cats")
@Tag(name = "Simpler Cats", description = "CRUD operations for simpler cat entities")
class SimplerCatController(service: SimplerCatService, mapper: SimplerCatMapper): SimplerController<UUID, SimplerCatEntity, SimplerCat, SimplerCatMapper, SimplerCatRepository, SimplerCatService>(
    typeName = "SimplerCat",
    service = service,
    mapper = mapper
)

@Service
class SimplerCatService(
    instantProvider: InstantProvider,
    crudRepository: CRUDRepository<UUID, SimplerCatEntity>,
    mapper: SimplerCatMapper
): SimplerService<UUID, SimplerCatEntity, SimplerCat, SimplerCatRepository, SimplerCatMapper>(
    typeName = "SimplerCat",
    instantProvider = instantProvider,
    crudRepository = crudRepository,
    mapper = mapper
)

@Component
class SimplerCatMapper: SimplerMapper<UUID, SimplerCatEntity, SimplerCat> {
    override fun entityToBeCreatedFrom(createModel: SimplerCat, now: Instant): SimplerCatEntity =
        SimplerCatEntity(
            id = UUID.randomUUID(),
            name = createModel.name,
            breed = createModel.breed,
            age = createModel.age,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override fun entityToModel(entity: SimplerCatEntity): SimplerCat =
        SimplerCat(
            id = requireNotNull(entity.id) { "SimplerCatEntity.id was null" },
            name = requireNotNull(entity.name) { "SimplerCatEntity.name was null" },
            breed = requireNotNull(entity.breed) { "SimplerCatEntity.breed was null" },
            age = requireNotNull(entity.age) { "SimplerCatEntity.age was null" },
            version = requireNotNull(entity.version) { "SimplerCatEntity.version was null" },
            createdAt = requireNotNull(entity.createdAt) { "SimplerCatEntity.createdAt was null" },
            updatedAt = requireNotNull(entity.updatedAt) { "SimplerCatEntity.updatedAt was null" },
            deletedAt = entity.deletedAt
        )

    override fun updateEntityWith(entity: SimplerCatEntity, updateModel: SimplerCat) {
        entity.apply {
            name = updateModel.name
            breed = updateModel.breed
            age = updateModel.age
        }
    }

    override fun createDTOToCreateModel(createDTO: SimplerCat): SimplerCat =
        SimplerCat(
            id = createDTO.id,
            name = createDTO.name,
            breed = createDTO.breed,
            age = createDTO.age,
            version = 0,
            createdAt = createDTO.createdAt,
            updatedAt = createDTO.updatedAt,
            deletedAt = createDTO.deletedAt
        )

    override fun modelToDTO(model: SimplerCat): SimplerCat =
        SimplerCat(
            id = model.id,
            name = model.name,
            breed = model.breed,
            age = model.age,
            version = model.version,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt,
            deletedAt = model.deletedAt
        )

    override fun updateDTOToUpdateModel(updateDTO: SimplerCat): SimplerCat =
        SimplerCat(
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
interface SimplerCatRepository: SimplerRepository<UUID, SimplerCatEntity>

@JakartaEntity
class SimplerCatEntity(
    @Id override var id: UUID?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): SimplerEntity<UUID, SimplerCatEntity>(id, version, createdAt, updatedAt, deletedAt) {
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

data class SimplerCat(
    val id: UUID,
    val name: String,
    val breed: String,
    val age: Int,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
): SimplerModel<UUID> {
    override fun id(): UUID = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}
