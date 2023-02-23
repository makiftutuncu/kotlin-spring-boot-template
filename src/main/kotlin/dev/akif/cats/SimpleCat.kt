package dev.akif.cats

import dev.akif.crud.common.InstantProvider
import dev.akif.crud.simple.*
import jakarta.persistence.Id
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.persistence.Entity as JakartaEntity
import java.time.Instant
import kotlin.math.absoluteValue
import kotlin.random.Random

@RestController
@RequestMapping("/simple-cats")
class SimpleCatController(service: SimpleCatService, mapper: SimpleCatMapper): SimpleController<Long, SimpleCatEntity, SimpleCat, SimpleCatDTO, SimpleCatMapper, SimpleCatService>("SimpleCat", service, mapper)

@Service
class SimpleCatService(
    instantProvider: InstantProvider,
    repository: SimpleRepository<Long, SimpleCatEntity>,
    mapper: SimpleCatMapper
): SimpleService<Long, SimpleCatEntity, SimpleCat, SimpleCatDTO, SimpleCatMapper>("SimpleCat", instantProvider, repository, mapper)

@Component
class SimpleCatMapper: SimpleMapper<Long, SimpleCatEntity, SimpleCat, SimpleCatDTO> {
    override fun entityToBeCreatedFrom(createModel: SimpleCat, now: Instant): SimpleCatEntity =
        SimpleCatEntity(
            id = Random.nextInt().absoluteValue.toLong(),
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
interface SimpleCatRepository: SimpleRepository<Long, SimpleCatEntity>

@JakartaEntity
class SimpleCatEntity(
    @Id override var id: Long?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): SimpleEntity<Long, SimpleCatEntity>(id, version, createdAt, updatedAt, deletedAt) {
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
    val id: Long,
    val name: String,
    val breed: String,
    val age: Int,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
): SimpleModel<Long> {
    override fun id(): Long = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}

data class SimpleCatDTO(
    val id: Long,
    val name: String,
    val breed: String,
    val age: Int,
    val createdAt: Instant,
    val updatedAt: Instant
): SimpleDTO<Long> {
    override fun id(): Long = id
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
}
