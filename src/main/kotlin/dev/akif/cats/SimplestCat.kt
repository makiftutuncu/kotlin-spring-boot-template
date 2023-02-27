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
    override fun updateEntityWith(entity: SimplestCat, updateModel: SimplestCat) {
        entity.apply {
            name = updateModel.name
            breed = updateModel.breed
            age = updateModel.age
        }
    }
}

@Repository
interface SimplestCatRepository: SimplestRepository<UUID, SimplestCat>

@JakartaEntity
class SimplestCat(
    @Id override var id: UUID? = null,
    var name: String? = null,
    var breed: String? = null,
    var age: Int? = null,
    override var version: Int? = null,
    override var createdAt: Instant? = null,
    override var updatedAt: Instant? = null,
    override var deletedAt: Instant? = null
): SimplestEntity<UUID>(id, version, createdAt, updatedAt, deletedAt)
