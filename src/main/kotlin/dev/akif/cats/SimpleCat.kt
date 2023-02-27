package dev.akif.cats

import dev.akif.crud.CRUDRepository
import dev.akif.crud.common.InstantProvider
import dev.akif.crud.simple.*
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
@RequestMapping("/simple-cats")
@Tag(name = "Simple Cats", description = "CRUD operations for simple cat entities")
class SimpleCatController(service: SimpleCatService, mapper: SimpleCatMapper): SimpleController<UUID, SimpleCatEntity, SimpleCat, SimpleCatDTO, SimpleCatMapper, SimpleCatRepository, SimpleCatService>(
    typeName = "SimpleCat",
    service = service,
    mapper = mapper
)

@Service
class SimpleCatService(
    instantProvider: InstantProvider,
    crudRepository: CRUDRepository<UUID, SimpleCatEntity>,
    mapper: SimpleCatMapper
): SimpleService<UUID, SimpleCatEntity, SimpleCat, SimpleCatDTO, SimpleCatRepository, SimpleCatMapper>(
    typeName = "SimpleCat",
    instantProvider = instantProvider,
    crudRepository = crudRepository,
    mapper = mapper
)

@Component
class SimpleCatMapper: SimpleMapper<UUID, SimpleCatEntity, SimpleCat, SimpleCatDTO> {
    override fun entityToBeCreatedFrom(createModel: SimpleCat, now: Instant): SimpleCatEntity =
        SimpleCatEntity(
            id = UUID.randomUUID(),
            name = createModel.name,
            breed = createModel.breed,
            age = createModel.age,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override fun entityToModel(entity: SimpleCatEntity): SimpleCat =
        SimpleCat(
            id = requireNotNull(entity.id) { "SimpleCatEntity.id was null" },
            name = requireNotNull(entity.name) { "SimpleCatEntity.name was null" },
            breed = requireNotNull(entity.breed) { "SimpleCatEntity.breed was null" },
            age = requireNotNull(entity.age) { "SimpleCatEntity.age was null" },
            version = requireNotNull(entity.version) { "SimpleCatEntity.version was null" },
            createdAt = requireNotNull(entity.createdAt) { "SimpleCatEntity.createdAt was null" },
            updatedAt = requireNotNull(entity.updatedAt) { "SimpleCatEntity.updatedAt was null" },
            deletedAt = entity.deletedAt
        )

    override fun updateEntityWith(entity: SimpleCatEntity, updateModel: SimpleCat) {
        entity.apply {
            name = updateModel.name
            breed = updateModel.breed
            age = updateModel.age
        }
    }

    override fun createDTOToCreateModel(createDTO: SimpleCatDTO): SimpleCat =
        SimpleCat(
            id = createDTO.id,
            name = createDTO.name,
            breed = createDTO.breed,
            age = createDTO.age,
            version = 0,
            createdAt = createDTO.createdAt,
            updatedAt = createDTO.updatedAt,
            deletedAt = null
        )

    override fun modelToDTO(model: SimpleCat): SimpleCatDTO =
        SimpleCatDTO(
            id = model.id,
            name = model.name,
            breed = model.breed,
            age = model.age,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )

    override fun updateDTOToUpdateModel(updateDTO: SimpleCatDTO): SimpleCat =
        SimpleCat(
            id = updateDTO.id,
            name = updateDTO.name,
            breed = updateDTO.breed,
            age = updateDTO.age,
            version = 0,
            createdAt = updateDTO.createdAt,
            updatedAt = updateDTO.updatedAt,
            deletedAt = null
        )
}

@Repository
interface SimpleCatRepository: SimpleRepository<UUID, SimpleCatEntity>

@JakartaEntity
class SimpleCatEntity(
    @Id override var id: UUID?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): SimpleEntity<UUID, SimpleCatEntity>(id, version, createdAt, updatedAt, deletedAt) {
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

data class SimpleCat(
    val id: UUID,
    val name: String,
    val breed: String,
    val age: Int,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
): SimpleModel<UUID> {
    override fun id(): UUID = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}

data class SimpleCatDTO(
    val id: UUID,
    val name: String,
    val breed: String,
    val age: Int,
    val createdAt: Instant,
    val updatedAt: Instant
): SimpleDTO<UUID> {
    override fun id(): UUID = id
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
}
