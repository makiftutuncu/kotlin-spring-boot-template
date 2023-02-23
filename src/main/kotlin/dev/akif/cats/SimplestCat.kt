package dev.akif.cats

import dev.akif.crud.common.InstantProvider
import dev.akif.crud.simplest.*
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
@RequestMapping("/simplest-cats")
class SimplestCatController(service: SimplestCatService, mapper: SimplestCatMapper): SimplestController<Long, SimplestCat, SimplestCatMapper, SimplestCatService>("SimplestCat", service, mapper)

@Service
class SimplestCatService(
    instantProvider: InstantProvider,
    repository: SimplestRepository<Long, SimplestCat>,
    mapper: SimplestCatMapper
): SimplestService<Long, SimplestCat, SimplestCatMapper>("SimplestCat", instantProvider, repository, mapper)

@Component
class SimplestCatMapper: SimplestMapper<Long, SimplestCat> {
    override fun entityToBeCreatedFrom(createModel: SimplestCat, now: Instant): SimplestCat =
        SimplestCat(
            id = Random.nextInt().absoluteValue.toLong(),
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
interface SimplestCatRepository: SimplestRepository<Long, SimplestCat>

@JakartaEntity
class SimplestCat(
    @Id override var id: Long?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): SimplestEntity<Long, SimplestCat>(id, version, createdAt, updatedAt, deletedAt) {
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
