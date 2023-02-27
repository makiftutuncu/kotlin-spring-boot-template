package dev.akif.cats

import dev.akif.crud.*
import dev.akif.crud.common.InstantProvider
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.persistence.Id
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*
import jakarta.persistence.Entity as JakartaEntity

@RestController
@RequestMapping("/cats")
@Tag(name = "Cats", description = "CRUD operations for cat entities")
class CatController(service: CatService, mapper: CatMapper): CRUDController<UUID, CatEntity, Cat, CatDTO, CreateCat, UpdateCat, CreateCatDTO, UpdateCatDTO, CatMapper, CatMapper, CatRepository, CatService>(
    typeName = "Cat",
    service = service,
    mapper = mapper
)

@Service
class CatService(
    instantProvider: InstantProvider,
    repository: CRUDRepository<UUID, CatEntity>,
    mapper: CatMapper
): CRUDService<UUID, CatEntity, Cat, CreateCat, UpdateCat, CatRepository, CatMapper>(
    typeName = "Cat",
    instantProvider = instantProvider,
    crudRepository = repository,
    mapper = mapper
)

@Component
class CatMapper: CRUDMapper<UUID, CatEntity, Cat, CreateCat, UpdateCat>, CRUDDTOMapper<UUID, Cat, CatDTO, CreateCat, UpdateCat, CreateCatDTO, UpdateCatDTO> {
    override fun entityToBeCreatedFrom(createModel: CreateCat, now: Instant): CatEntity =
        CatEntity(
            id = UUID.randomUUID(),
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
            age = updateDTO.age
        )
}

@Repository
interface CatRepository: CRUDRepository<UUID, CatEntity>

@JakartaEntity
class CatEntity(
    @Id override var id: UUID?,
    var name: String?,
    var breed: String?,
    var age: Int?,
    override var version: Int?,
    override var createdAt: Instant?,
    override var updatedAt: Instant?,
    override var deletedAt: Instant?
): CRUDEntity<UUID>(id, version, createdAt, updatedAt, deletedAt) {
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

data class UpdateCat(val name: String, val age: Int): CRUDUpdateModel

data class Cat(
    val id: UUID,
    val name: String,
    val breed: String,
    val age: Int,
    val version: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?
): CRUDModel<UUID> {
    override fun id(): UUID = id
    override fun version(): Int = version
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
    override fun deletedAt(): Instant? = deletedAt
}

data class CreateCatDTO(val name: String, val breed: String, val age: Int): CRUDCreateDTO

data class UpdateCatDTO(val name: String, val age: Int): CRUDUpdateDTO

data class CatDTO(
    val id: UUID,
    val name: String,
    val breed: String,
    val age: Int,
    val createdAt: Instant,
    val updatedAt: Instant
): CRUDDTO<UUID> {
    override fun id(): UUID = id
    override fun createdAt(): Instant = createdAt
    override fun updatedAt(): Instant = updatedAt
}
