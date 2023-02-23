package dev.akif.cats

import dev.akif.crud.*
import dev.akif.crud.common.InstantProvider
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
@RequestMapping("/cats")
class CatController(service: CatService, mapper: CatMapper): CRUDController<Long, CatEntity, Cat, CatDTO, CreateCat, UpdateCat, CreateCatDTO, UpdateCatDTO, CatMapper, CatMapper, CatService>("Cat", service, mapper)

@Service
class CatService(
    instantProvider: InstantProvider,
    repository: CRUDRepository<Long, CatEntity>,
    mapper: CatMapper
): CRUDService<Long, CatEntity, Cat, CreateCat, UpdateCat, CatMapper>("Cat", instantProvider, repository, mapper)

@Component
class CatMapper: CRUDMapper<Long, CatEntity, Cat, CreateCat, UpdateCat>, CRUDDTOMapper<Long, Cat, CatDTO, CreateCat, UpdateCat, CreateCatDTO, UpdateCatDTO> {
    override fun entityToBeCreatedFrom(createModel: CreateCat, now: Instant): CatEntity =
        CatEntity(
            id = Random.nextInt().absoluteValue.toLong(),
            name = createModel.name,
            breed = createModel.breed,
            age = createModel.age,
            version = 0,
            createdAt = now,
            updatedAt = now,
            deletedAt = null
        )

    override fun entityToModel(entity: CatEntity): Cat =
        Cat(
            id = requireNotNull(entity.id) { "CatEntity.id was null" },
            name = requireNotNull(entity.name) { "CatEntity.name was null" },
            breed = requireNotNull(entity.breed) { "CatEntity.breed was null" },
            age = requireNotNull(entity.age) { "CatEntity.age was null" },
            version = requireNotNull(entity.version) { "CatEntity.version was null" },
            createdAt = requireNotNull(entity.createdAt) { "CatEntity.createdAt was null" },
            updatedAt = requireNotNull(entity.updatedAt) { "CatEntity.updatedAt was null" },
            deletedAt = entity.deletedAt
        )

    override fun updateEntityWith(entity: CatEntity, updateModel: UpdateCat) {
        entity.apply {
            name = updateModel.name
            breed = updateModel.breed
            age = updateModel.age
        }
    }

    override fun createDTOToCreateModel(createDTO: CreateCatDTO): CreateCat =
        CreateCat(
            name = createDTO.name,
            breed = createDTO.breed,
            age = createDTO.age
        )

    override fun modelToDTO(model: Cat): CatDTO =
        CatDTO(
            id = model.id,
            name = model.name,
            breed = model.breed,
            age = model.age,
            createdAt = model.createdAt,
            updatedAt = model.updatedAt
        )

    override fun updateDTOToUpdateModel(updateDTO: UpdateCatDTO): UpdateCat =
        UpdateCat(
            name = updateDTO.name,
            breed = updateDTO.breed,
            age = updateDTO.age
        )
}

@Repository
interface CatRepository: CRUDRepository<Long, CatEntity>

@JakartaEntity
class CatEntity(
    @Id override var id: Long?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): CRUDEntity<Long, CatEntity>(id, version, createdAt, updatedAt, deletedAt) {
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

    override fun toString(): String {
        return "CatEntity(id=$id, name=$name, breed=$breed, age=$age, version=$version, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
    }
}

data class CreateCat(val name: String, val breed: String, val age: Int): CRUDCreateModel

data class UpdateCat(val name: String, val breed: String, val age: Int): CRUDUpdateModel

data class Cat(
    val id: Long,
    val name: String,
    val breed: String,
    val age: Int,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
): CRUDModel<Long> {
    override fun id(): Long = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}

data class CreateCatDTO(val name: String, val breed: String, val age: Int): CRUDCreateDTO

data class UpdateCatDTO(val name: String, val breed: String, val age: Int): CRUDUpdateDTO

data class CatDTO(
    val id: Long,
    val name: String,
    val breed: String,
    val age: Int,
    val createdAt: Instant,
    val updatedAt: Instant
): CRUDDTO<Long> {
    override fun id(): Long = id
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
}
