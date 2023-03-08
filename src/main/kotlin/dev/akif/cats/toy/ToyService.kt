package dev.akif.cats.toy

import dev.akif.crud.CRUDRepository
import dev.akif.crud.CRUDService
import dev.akif.crud.common.InstantProvider
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ToyService(
    instantProvider: InstantProvider,
    repository: CRUDRepository<UUID, ToyEntity>,
    mapper: ToyMapper
) : CRUDService<UUID, ToyEntity, Toy, CreateToy, UpdateToy, ToyRepository, ToyMapper>(
    typeName = "Toy",
    instantProvider = instantProvider,
    crudRepository = repository,
    mapper = mapper
)
