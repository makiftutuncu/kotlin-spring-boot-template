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
}

@Repository
interface SimplerCatRepository: SimplerRepository<UUID, SimplerCatEntity>

@JakartaEntity
class SimplerCatEntity(
    @Id override var id: UUID? = null,
    var name: String? = null,
    var breed: String? = null,
    var age: Int? = null,
    override var version: Int? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
    override var deletedAt: Instant? = null
): SimplerEntity<UUID>(id, version, createdAt, updatedAt, deletedAt)

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
