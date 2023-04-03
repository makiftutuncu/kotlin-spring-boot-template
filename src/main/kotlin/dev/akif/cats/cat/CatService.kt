package dev.akif.cats.cat

import dev.akif.crud.CRUDService
import dev.akif.crud.common.InstantProvider
import dev.akif.crud.common.Parameters
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CatService(
    instantProvider: InstantProvider,
    repository: CatRepository,
    mapper: CatMapper
) : CRUDService<UUID, CatEntity, Cat, CreateCat, UpdateCat, CatRepository, CatMapper>(
    typeName = "Cat",
    instantProvider = instantProvider,
    repository = repository,
    mapper = mapper
) {
    override fun createUsingRepository(entity: CatEntity, parameters: Parameters): CatEntity =
        repository.save(entity)

    override fun getUsingRepository(id: UUID, parameters: Parameters): CatEntity? =
        repository.findByIdAndDeletedAtIsNull(id)

    override fun listUsingRepository(pageable: Pageable, parameters: Parameters): Page<CatEntity> =
        repository.findAllByDeletedAtIsNull(pageable)

    override fun updateUsingRepository(entity: CatEntity, parameters: Parameters): Int =
        repository.update(entity)
}
