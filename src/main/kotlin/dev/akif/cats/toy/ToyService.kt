package dev.akif.cats.toy

import dev.akif.cats.cat.Cat
import dev.akif.cats.cat.CatService
import dev.akif.crud.CRUDService
import dev.akif.crud.common.InstantProvider
import dev.akif.crud.common.Parameters
import dev.akif.crud.error.CRUDErrorException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ToyService(
    instantProvider: InstantProvider,
    repository: ToyRepository,
    mapper: ToyMapper,
    private val cats: CatService
) : CRUDService<Long, ToyEntity, Toy, CreateToy, UpdateToy, ToyRepository, ToyMapper>(
    typeName = "Toy",
    instantProvider = instantProvider,
    repository = repository,
    mapper = mapper
) {
    override fun createUsingRepository(entity: ToyEntity, parameters: Parameters): ToyEntity =
        repository.save(entity)

    override fun getUsingRepository(id: Long, parameters: Parameters): ToyEntity? =
        repository.findByIdAndCatIdAndDeletedAtIsNull(id, getCat(parameters).id)

    override fun listUsingRepository(pageable: Pageable, parameters: Parameters): Page<ToyEntity> =
        repository.findAllByCatIdAndDeletedAtIsNull(getCat(parameters).id, pageable)

    override fun updateUsingRepository(entity: ToyEntity, parameters: Parameters): Int =
        repository.update(entity)

    private fun getCat(parameters: Parameters): Cat {
        val catId = requireNotNull(parameters.pathVariable("catId") { UUID.fromString(it) }) { "catId is required." }
        return cats.get(catId, parameters) ?: throw CRUDErrorException.notFound(cats.typeName, catId)
    }
}
