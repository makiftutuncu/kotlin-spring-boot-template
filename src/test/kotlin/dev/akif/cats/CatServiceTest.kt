package dev.akif.cats

import dev.akif.crud.CRUDRepository
import dev.akif.crud.CRUDServiceTest
import org.junit.jupiter.api.DisplayName

@DisplayName("CatService")
class CatServiceTest : CRUDServiceTest<Long, CatEntity, Cat, CreateCat, UpdateCat, CatMapper, CatService, CatTestData>(
    "Cat", CatMapper(), CatTestData()
) {
    override fun buildService(repository: CRUDRepository<Long, CatEntity>, mapper: CatMapper): CatService =
        CatService(testData.instantProvider, repository, mapper)
}
