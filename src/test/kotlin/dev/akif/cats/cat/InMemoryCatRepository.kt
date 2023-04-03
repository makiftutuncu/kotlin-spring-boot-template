package dev.akif.cats.cat

import dev.akif.crud.InMemoryCRUDRepository
import java.util.UUID

object InMemoryCatRepository: InMemoryCRUDRepository<UUID, CatEntity, CreateCat, CatTestData>(CatTestData), CatRepository
