package dev.akif.cats.toy

import dev.akif.cats.cat.CatMapper
import dev.akif.cats.cat.CatService
import dev.akif.cats.cat.InMemoryCatRepository
import dev.akif.cats.toy.*
import dev.akif.crud.CRUDServiceTest
import org.junit.jupiter.api.DisplayName

@DisplayName("ToyService")
class ToyServiceTest : CRUDServiceTest<Long, ToyEntity, Toy, CreateToy, UpdateToy, ToyMapper, ToyRepository, ToyService, ToyTestData>(
    mapper = ToyMapper(),
    testData = ToyTestData,
    buildService = { mapper, testData ->
        val cats = CatService(testData.instantProvider, InMemoryCatRepository, CatMapper(mapper))
        ToyService(testData.instantProvider, InMemoryToyRepository, mapper, cats)
    }
)
