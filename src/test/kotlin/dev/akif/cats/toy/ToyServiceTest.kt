package dev.akif.cats.toy

import dev.akif.cats.toy.*
import dev.akif.crud.CRUDServiceTest
import org.junit.jupiter.api.DisplayName
import java.util.UUID

@DisplayName("ToyService")
class ToyServiceTest : CRUDServiceTest<UUID, ToyEntity, Toy, CreateToy, UpdateToy, ToyMapper, ToyRepository, ToyService, ToyTestData>(
    typeName = "Toy",
    mapper = ToyMapper(),
    testData = ToyTestData()
) {
    override fun buildService(mapper: ToyMapper, testData: ToyTestData): ToyService =
        ToyService(testData.instantProvider, testData.repository, mapper)
}
