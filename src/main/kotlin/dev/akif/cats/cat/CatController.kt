package dev.akif.cats.cat

import dev.akif.crud.CRUDController
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/cats")
@Tag(name = "Cats", description = "CRUD operations for cat entities")
class CatController(service: CatService, mapper: CatDTOMapper) :
    CRUDController<UUID, CatEntity, Cat, CatDTO, CreateCat, UpdateCat, CreateCatDTO, UpdateCatDTO, CatMapper, CatDTOMapper, CatRepository, CatService>(
        typeName = "Cat",
        service = service,
        mapper = mapper
    )
