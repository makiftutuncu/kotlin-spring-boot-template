package dev.akif.cats.cat

import dev.akif.cats.toy.InMemoryToyRepository
import dev.akif.cats.toy.ToyMapper
import dev.akif.crud.CRUDServiceTest
import org.junit.jupiter.api.DisplayName
import java.util.UUID

@DisplayName("CatService")
class CatServiceTest : CRUDServiceTest<UUID, CatEntity, Cat, CreateCat, UpdateCat, CatMapper, CatRepository, CatService, CatTestData>(
    mapper = CatMapper(ToyMapper()),
    testData = CatTestData,
    buildService = { mapper, testData -> CatService(testData.instantProvider, InMemoryCatRepository, mapper) }
) {
    override fun resetData() {
        InMemoryToyRepository.reset()
        InMemoryCatRepository.reset()
    }
}
