package dev.akif.cats

import dev.akif.crud.CRUDRepository
import dev.akif.crud.CRUDService
import dev.akif.crud.common.InstantProvider
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CatService(
    instantProvider: InstantProvider,
    repository: CRUDRepository<UUID, CatEntity>,
    mapper: CatMapper
) : CRUDService<UUID, CatEntity, Cat, CreateCat, UpdateCat, CatRepository, CatMapper>(
    typeName = "Cat",
    instantProvider = instantProvider,
    crudRepository = repository,
    mapper = mapper
)
