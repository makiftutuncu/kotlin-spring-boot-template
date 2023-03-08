package dev.akif.cats.toy

import dev.akif.crud.CRUDController
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/cats/{catId}/toys")
@Tag(name = "Toys", description = "CRUD operations for toys of cats")
class ToyController(service: ToyService, mapper: ToyDTOMapper) :
    CRUDController<UUID, ToyEntity, Toy, ToyDTO, CreateToy, UpdateToy, CreateToyDTO, UpdateToyDTO, ToyMapper, ToyDTOMapper, ToyRepository, ToyService>(
        typeName = "Toy",
        service = service,
        mapper = mapper
    )
