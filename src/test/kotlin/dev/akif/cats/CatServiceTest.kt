package dev.akif.cats

import dev.akif.crud.CRUDServiceTest
import org.junit.jupiter.api.DisplayName
import java.util.UUID

@DisplayName("CatService")
class CatServiceTest : CRUDServiceTest<UUID, CatEntity, Cat, CreateCat, UpdateCat, CatMapper, CatRepository, CatService, CatTestData>(
    typeName = "Cat",
    mapper = CatMapper(),
    testData = CatTestData()
) {
    override fun buildService(mapper: CatMapper, testData: CatTestData): CatService =
        CatService(testData.instantProvider, testData.repository, mapper)
}
