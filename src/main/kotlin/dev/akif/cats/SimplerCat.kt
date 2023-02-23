package dev.akif.cats

import dev.akif.crud.common.InstantProvider
import dev.akif.crud.simpler.*
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
@RequestMapping("/simpler-cats")
class SimplerCatController(service: SimplerCatService, mapper: SimplerCatMapper): SimplerController<Long, SimplerCatEntity, SimplerCat, SimplerCatMapper, SimplerCatService>("SimplerCat", service, mapper)

@Service
class SimplerCatService(
    instantProvider: InstantProvider,
    repository: SimplerRepository<Long, SimplerCatEntity>,
    mapper: SimplerCatMapper
): SimplerService<Long, SimplerCatEntity, SimplerCat, SimplerCatMapper>("SimplerCat", instantProvider, repository, mapper)

@Component
class SimplerCatMapper: SimplerMapper<Long, SimplerCatEntity, SimplerCat> {
    override fun entityToBeCreatedFrom(createModel: SimplerCat, now: Instant): SimplerCatEntity =
        SimplerCatEntity(
            id = Random.nextInt().absoluteValue.toLong(),
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
interface SimplerCatRepository: SimplerRepository<Long, SimplerCatEntity>

@JakartaEntity
class SimplerCatEntity(
    @Id override var id: Long?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): SimplerEntity<Long, SimplerCatEntity>(id, version, createdAt, updatedAt, deletedAt) {
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
    val id: Long,
    val name: String,
    val breed: String,
    val age: Int,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
): SimplerModel<Long> {
    override fun id(): Long = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}
